package network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Client {

    private String host;
    private int port;
    private int reconnectionTimeout;
    private int reconnectionAttempts;
    private int maxReconnectionAttempts;

    private DatagramSocket socket;

    public Client(String host, int port, int reconnectionTimeout, int maxReconnectionAttempts) {
        this.host = host;
        this.port = port;
        this.reconnectionTimeout = reconnectionTimeout;
        this.maxReconnectionAttempts = maxReconnectionAttempts;
    }

    public Response sendRequest(Request request) throws InterruptedException, IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(6400);
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(request);
        final byte[] data = baos.toByteArray();

        for (int reconnectionAttempts = 0; reconnectionAttempts < maxReconnectionAttempts; reconnectionAttempts++) {
            final DatagramPacket packet = new DatagramPacket(data, data.length);
            this.socket.send(packet);
            this.socket.close();
        }
        return new Response("ok");
    }
}
