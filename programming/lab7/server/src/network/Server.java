package network;

import handlers.CommandHandler;
import handlers.DatabaseHandler;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Server {
    private InetAddress address;
    private int port;

    private static final Logger serverLogger = Logger.getLogger("logger");

    private DatagramSocket socket;

    private DatabaseHandler databaseHandler;

    ExecutorService requestReadingService = Executors.newFixedThreadPool(3); // Fixed thread pool
    ExecutorService requestProcessingService = Executors.newCachedThreadPool();
    ExecutorService responseSendingService = Executors.newCachedThreadPool();

    BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)));

    public Server(String address, int port, DatabaseHandler databaseHandler) throws Exception {
         this.socket = new DatagramSocket(port);
         this.port = port;
         this.address = InetAddress.getByName(address);
         this.databaseHandler = databaseHandler;

         socket.setSoTimeout(0); // non-blocking mode
    }

    public void sendPacket(String text, InetAddress address, Integer port) throws Exception {
        var response = text.getBytes();
        this.socket.send(new DatagramPacket(response, response.length, address, port));
    }
    public DatagramPacket receivePacket() throws Exception {
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        this.socket.receive(packet);
        return packet;
    }
    public Request getRequestFromPacket(DatagramPacket packet) throws Exception {
        var is = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(packet.getData())));
        return (Request) is.readObject();
    }

    public Runnable readRequest() {
        while (true) {
            try {
                var packet = this.receivePacket();
                requestProcessingService.submit(processRequest(packet)).get();
            } catch (Exception e) {
//                System.out.println(e);
            }
        }
    }

    public Runnable processRequest(DatagramPacket packet) throws Exception {
        var response = executeRequest(packet, databaseHandler).call();
        responseSendingService.submit(()->{
            try {
                this.sendPacket(response, address, packet.getPort());
            } catch (Exception e) {
                System.out.println("Couldn't send response: " + e.getMessage());
            }
        }).get();
        return null;
    }

    public Callable<String> executeRequest(DatagramPacket packet, DatabaseHandler databaseHandler) throws Exception {
        return ()->{
            var request = this.getRequestFromPacket(packet);

            var userExists = databaseHandler.UserExists(request.login);

            // Unauthorized command sssection
            switch (request.type) {
                case "login":
                    if (!userExists) {
                        return "false";
                    }
                    var id = databaseHandler.AuthorizeUser(request.login, request.password);
                    return id != -1 ? "true" : "false";
                case "register":
                    if (userExists) {
                        return "false";
                    }
                    id = databaseHandler.CreateUser(request.login, request.password);
                    return id != -1 ? "true" : "false";
            }

            var userID = databaseHandler.AuthorizeUser(request.login, request.password);

            if (userID == -1) {
                return "Not authorized";
            }

            // Authorized command section
            switch (request.type) {
                case "run_command":
                    var line = String.join(" ", request.getArgs());
                    return CommandHandler.process(line, userID, this.databaseHandler, reader);
                case "save_route":
                    var route = request.getRoute();
                    if (databaseHandler.getCollection().get(route.key) != null) {
                        return "Route with key " + route.key + " already exists";
                    }
                    route.userID = userID;
                    databaseHandler.insertRoute(route, userID);
                    return "ok";
                case "key_availability_check":
                    return databaseHandler.getCollection().get(request.keyToBeChecked) != null ? "busy" : "free";
                default:
                    return "";
            }
        };
    }

    public void Run() throws ExecutionException, InterruptedException {
        // Reading command-line input in new thread
        new Thread(()->{
            serverLogger.info("Server launched");
            while (true) {
                System.out.print("shell>>");
                try {
                    var line = reader.readLine();
                    System.out.println(CommandHandler.process(line, 0, this.databaseHandler, this.reader));
                } catch (Exception e) {
                    serverLogger.warning(e.toString());
                }
            }
        }).start();

        // Launching 5 threads to read incoming requests
        for (var i = 0; i < 3; i++) {
            requestReadingService.submit(this::readRequest).get();
        }
        requestProcessingService.shutdown();
        requestReadingService.shutdown();
        responseSendingService.shutdown();
    }
}
