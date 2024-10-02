package handlers;

import commands.Insert;
import commands.RemoveByKey;
import entities.Route;
import interfaces.Command;
import network.Client;
import network.Request;

import java.io.BufferedReader;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

public class CommandHandler {
    private Client client; // UDP client

    public CommandHandler(Client client) {
        this.client = client;
    }

    private static Stack<String> handledScripts = new Stack<>();

    private void handleUpdate(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Key is empty");
            return;
        }
        var key = Integer.parseInt(args[1]);
        client.sendRequest(new Request(new RemoveByKey(), args));

        var r = new Route(key, new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in))));
        System.out.println(client.sendRequest(new Request(new Insert(), r)).getResult());
    }

    private void handleInsert(Integer key, BufferedReader reader) throws Exception {
        var keyFree = client.sendRequest(new Request(key)).getResult().equals("free");
        if (!keyFree) {
            System.out.println("Route with key " + key +  " already exists");
            return;
        }
        var r = new Route(key, reader);
        System.out.println(r);
        System.out.println(client.sendRequest(new Request(new Insert(), r)).getResult());
    }

    private void handleExit() {
        System.exit(0);
    }

    public static String getNextCommand(BufferedReader fileReader) throws Exception {
        var x = -1;
        var routeKey = -1;
        var text = "";
        var line = "";
        while ((line = fileReader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }

            if (line.startsWith("insert")) {
                text = line;
                x = 11;
                continue;
            } else if (x == 0) { // Returning full body of "insert" command
                text += "\n" + line;
                var textCopy = text;
                x = -1;
                text = "";
                return textCopy;
            } else if (x > 0) {
                x--;
                text += "\n" + line;
                continue;
            }
            return line;
        }
        return "";
    }

    // handleExecuteScript opens file to be executed, reads it and send each command in new request
    private void handleExecuteScript(String filepath) {
        if (handledScripts.contains(filepath)) {
            System.out.println("WARN: " + filepath + " cannot be called twice, skipping...");
            return;
        }

        handledScripts.add(filepath);

        try {
            var fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));

            for (String command = getNextCommand(fileReader); !Objects.equals(command, ""); command = getNextCommand(fileReader)) {
                System.out.println("$ " + command);
                var args = command.split("\n")[0].split(" ");

                if (command.startsWith("execute_script")) {
                    handleExecuteScript(args[1]);
                    continue;
                } else if (command.startsWith("insert")) {
                    Integer key;
                    try {
                        key = Integer.parseInt(args[1]);
                    } catch (Exception e) {
                        System.out.println("Invalid key provided");
                        continue;
                    }
                    var reader = new BufferedReader(new StringReader(command));
                    reader.readLine();
                    handleInsert(key, reader);
                    continue;
                }
                // TODO: update command
                System.out.println(client.sendRequest(new Request(CommandParser.getCommand(command), args)).getResult());
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("File not found");
        } finally {
            handledScripts.remove(filepath);
        }
    }


    /**
     * Processes the given command line using the provided CollectionHandler.
     *
     * @param line the command line to process
     */
    public void process(String line, Client client, BufferedReader reader){
        if (line == null) {
            System.exit(0);
            return;
        }
        if (line.isBlank()) {
            System.out.println("Command cannot be empty!");
            return;
        }

        var args = line.split(" ");

        Command command = CommandParser.getCommand(args[0].strip());
        if (command == null) {
            System.out.println("Unknown command");
            return;
        }

        try {
            switch (command.getName()) {
                case "update":
                    handleUpdate(args);
                    break;
                case "insert":
                    Integer key;
                    try {
                        key = Integer.parseInt(args[1]);
                    } catch (Exception e) {
                        System.out.println("Invalid key provided");
                        return;
                    }
                    handleInsert(key, reader);
                    break;
                case "exit":
                    handleExit();
                    break;
                case "execute_script":
                    var filepath = args[1];
                    handleExecuteScript(filepath);
                    break;
                default:
                    System.out.println(client.sendRequest(new Request(command, args)).getResult());
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
