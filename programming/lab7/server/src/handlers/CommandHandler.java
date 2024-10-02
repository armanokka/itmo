package handlers;

import interfaces.Command;

import java.io.BufferedReader;

public class CommandHandler {

    /**
     * Processes the given command line using the provided CollectionHandler.
     *
     * @param line the command line to process
     * @param databaseHandler the DatabaseHandler to use for processing the command
     */
    public static String process(String line, Integer userID, DatabaseHandler databaseHandler, BufferedReader reader){
        if (line == null) {
            System.exit(0);
            return "";
        }
        if (line.isBlank()) {
            return "Command cannot be empty!";
        }
        databaseHandler.saveCommandHistory(line);

        var args = line.split(" ");

        Command command = PackageParser.getCommand(args[0].strip());
        if (command == null) {
            return "Unknown command";
        }
        return command.execute(databaseHandler, userID, args, reader);
    }

}