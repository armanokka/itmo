import commands.Clear;
import handlers.*;
import interfaces.Command;
import network.Client;
import network.Request;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.MessageDigest;

class Main{
    public static void main(String [] args) throws Exception {
        var reader = new BufferedReader(new InputStreamReader(System.in));

        var client = new Client("localhost", 9877, 5000, 5);
        client.authenticate(reader);
        var commandHandler = new CommandHandler(client);

        while (true) {
            System.out.print("shell>>");
            var line = reader.readLine();

            commandHandler.process(line, reader);
        }
    }
}