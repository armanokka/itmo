import commands.Clear;
import handlers.CollectionHandler;
import handlers.DatabaseHandler;
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
    private static String host;
    private static String user;
    private static String password;
    private static String dbname;
    private static String schema;
    private static Integer port;

    public static void loadEnv() {
        host = System.getenv("POSTGRES_HOST");
        user = System.getenv("POSTGRES_USER");
        password = System.getenv("POSTGRES_PASSWORD");
        dbname = System.getenv("POSTGRES_DBNAME");
        schema = System.getenv("POSTGRES_SCHEMA");
        try {
            port = Integer.parseInt(System.getenv("POSTGRES_PORT"));
        } catch (Exception e) {
            System.out.println("$POSTGRES_PORT is invalid");
        }
    }

    public static void main(String[] args) {
        loadEnv();

        var databaseHandler =  new DatabaseHandler(host, port, user, password, dbname, schema);

        try {
            new Server("localhost", 9877, databaseHandler).Run();
        } catch (Exception e) {
            System.out.println("Error while igniting server: " + e.toString());
        }
        System.out.println("Server closed");
    }
}