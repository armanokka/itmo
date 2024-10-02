package network;

import commands.Save;
import entities.Route;
import handlers.CollectionHandler;
import handlers.CommandHandler;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Logger;

public class Server {
    private InetAddress address;
    private int port;

    private static final Logger serverLogger = Logger.getLogger("logger");

    private DatagramSocket socket;

    private CollectionHandler collectionHandler = new CollectionHandler();

    BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)));

    public Server(String address, int port) throws Exception {
         this.socket = new DatagramSocket(port);
         this.port = port;
         this.address = InetAddress.getByName(address);

         socket.setSoTimeout(0); // non-blocking mode
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

    public void sendPacket(String text, InetAddress address, Integer port) throws Exception {
        var response = text.getBytes();
        this.socket.send(new DatagramPacket(response, response.length, address, port));
    }


    public void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    System.out.println("\nSaving collection...");
                    new Save().execute(collectionHandler, null, reader);
                    System.out.println("Shutdown successfully");
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });
    }

    public void Run() {
        addShutdownHook();

        // Reading command-line input
        new Thread(()->{
            serverLogger.info("Server launched");
            while (true) {
                System.out.print("shell>>");
                try {
                    var line = reader.readLine();
                    System.out.println(CommandHandler.process(line, this.collectionHandler, this.reader));
                } catch (Exception e) {
                    serverLogger.warning(e.toString());
                }
            }
        }).start();

        // TODO fix bug when usedKeys in client is null and user can create route
        while (true) {
            try {
                var packet = this.receivePacket();
                var request = this.getRequestFromPacket(packet);

                if (request.keyToBeChecked != null) { // We got request to check if key is free to take
                    String response = Route.usedKeys.contains(request.keyToBeChecked) ? "busy" : "free";
                    this.sendPacket(response, address, packet.getPort());
                    continue;
                }

                serverLogger.info("received " + request);
                switch (request.getCommand().getName()) {
                    case "insert":
                        var collection = collectionHandler.getCollection();
                        var r = request.getRoute();
                        collection.put(r.getKey(), r);
                        collectionHandler.updateCollection(collection);

                        serverLogger.info("response sent");
                        this.sendPacket("ok", address, packet.getPort());
                        break;
                    case "execute_script":
                        this.sendPacket("", address, packet.getPort());
                        break;
                    default:
                        var line = String.join(" ", request.getArgs());
                        var response = CommandHandler.process(line, this.collectionHandler, reader);
                        this.sendPacket(response, address, packet.getPort());
                        break;
                }
                serverLogger.info("response sent");
            } catch (Exception e) {
                serverLogger.warning(e.toString());
                e.printStackTrace();
                continue;
            }
        }
    }
}
