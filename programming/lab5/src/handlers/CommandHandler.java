package handlers;

import interfaces.Command;

import java.io.BufferedReader;

public class CommandHandler {

    /**
     * Processes the given command line using the provided CollectionHandler.
     *
     * @param line the command line to process
     * @param collectionHandler the CollectionHandler to use for processing the command
     */
    public static void process(String line, CollectionHandler collectionHandler, BufferedReader reader){
        if (line == null) {
            System.exit(0);
            return;
        }
        if (line.isBlank()) {
            System.out.println("Command cannot be empty!");
            return;
        }
        collectionHandler.saveCommandHistory(line);

        var args = line.split(" ");

        Command command = PackageParser.getCommand(args[0].strip());
        if (command == null) {
            System.out.println("Unknown command");
            return;
        }

        var invoker = new CommandInvoker();

        invoker.setCommand(command);
        invoker.executeCommand(collectionHandler, args, reader);
    }
}
