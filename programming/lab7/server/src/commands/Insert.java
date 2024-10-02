package commands;

import entities.Route;
import handlers.CollectionHandler;
import handlers.DatabaseHandler;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.Serial;
import java.io.Serializable;

/**
 * A command implementation to insert a new element into the collection.
 */
public class Insert implements Command, Serializable {
    @Serial
    private static final long serialVersionUID = 11L;
    /**
     * Returns the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "insert";
    }

    /**
     * Provides a description of the command.
     * @return A string containing the command's description.
     */
    @Override
    public String getDescription() {
        return "insert                          -- create a new element with the given key";
    }

    /**
     * Executes the insert command.
     * Creates a new element with the given key and inserts it into the collection.
     * @param databaseHandler The collection handler.
     * @param args Arguments for command execution. Expects the key as the second argument.
     */
    @Override
    public String execute(DatabaseHandler databaseHandler, Integer userID,  String[] args, BufferedReader reader) {
        if (args.length != 2) {
            return "invalid number of arguments";
        }

        Integer key = -1;
        try {
            key = Integer.parseInt(args[1]);
        } catch (Exception e) {
            return "invalid key provided. Must be integer";
        }
        var r = new Route(key, userID, reader);
        try {
            databaseHandler.insertRoute(r, userID);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }
}
