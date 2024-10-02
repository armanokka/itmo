import commands.Clear;
import handlers.CollectionHandler;
import interfaces.Command;
import network.Request;
import network.Server;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) {
        try {
            new Server("localhost", 1051).Run();
        } catch (Exception e) {
            System.out.println("Error while igniting server: " + e.toString());
        }
        System.out.println("Server closed");
    }
}