package network;

import commands.Clear;

import java.io.*;
import java.net.*;
import java.util.Objects;

/**
 * The Client class represents a client that sends requests to a server using UDP protocol.
 */
public class Client {

    private final String host; // the host address of the server
    private final int port; // the port number of the server
    private final int responseTimeout; // the timeout for waiting for a response
    private final int maxReconnectionAttempts; // the maximum number of reconnection attempts


    private DatagramSocket socket;  // the UDP socket used for communication
    // TODO execute_script
    // TODO save and show data in the save order
    /**
     * Constructs a new Client with the specified host, port, response timeout, and maximum reconnection attempts.
     *
     * @param host the host address of the server
     * @param port the port number of the server
     * @param responseTimeout the timeout for waiting for a response
     * @param maxReconnectionAttempts the maximum number of reconnection attempts
     */
    public Client(String host, int port, int responseTimeout, int maxReconnectionAttempts) {
        this.host = host;
        this.port = port;
        this.responseTimeout = responseTimeout;
        this.maxReconnectionAttempts = maxReconnectionAttempts;
    }

    /**
     * Sends a request to the server and waits for a response.
     *
     * @param request the request to be sent
     * @return the response received from the server
     * @throws InterruptedException if the thread is interrupted while waiting
     * @throws IOException if an I/O error occurs while sending or receiving the request
     */
    public Response sendRequest(Request request) throws InterruptedException, IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(request);
        final byte[] data = baos.toByteArray();
        InetAddress address = InetAddress.getByName(host);
        var socket = new DatagramSocket();

        for (var attempts = 0 ; attempts < maxReconnectionAttempts; attempts++) {
            final DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);

            DatagramPacket responsePacket = new DatagramPacket(new byte[20480], 20480, address, port);
            try {
                socket.setSoTimeout(responseTimeout);
                socket.receive(responsePacket);
                socket.close();
                return new Response(new String(responsePacket.getData(), responsePacket.getOffset(), responsePacket.getLength()));
            } catch (SocketTimeoutException e) {
                System.out.println("No response from the server. Retrying in 5 seconds...");
                Thread.sleep(5000);
            }
        }
        socket.close();
        return new Response("Server is unreachable");
    }

    public boolean checkIDAvailability(Integer id) throws Exception {
        return sendRequest(new Request(id)).getResult().equals("free");
    }
}
