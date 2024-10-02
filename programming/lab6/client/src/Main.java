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

class Main{
    public static void main(String [] args) throws Exception {
        var client = new Client("localhost", 1051, 5000, 5);
        var commandHandler = new CommandHandler(client);
        var reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("shell>>");
            var line = reader.readLine();

            commandHandler.process(line, client, reader);
        }
    }
}