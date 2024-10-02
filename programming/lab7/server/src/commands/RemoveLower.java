package commands;

import entities.Route;
import handlers.CollectionHandler;
import handlers.DatabaseHandler;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.Serial;
import java.io.Serializable;
import java.util.Hashtable;

/**
 * A command implementation to remove all elements lower than a given key from the collection.
 */
public class RemoveLower implements Command, Serializable {
    @Serial
    private static final long serialVersionUID = 14L;
    /**
     * Returns the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "remove_lower_key";
    }

    /**
     * Provides a description of the command.
     * @return A string containing the command's description.
     */
    @Override
    public String getDescription() {
        return getName() + " {key}          -- remove all elements from collection that is lower than given key\n";
    }

    /**
     * Executes the remove lower command.
     * Removes all elements from the collection that have keys lower than the provided key.
     * @param databaseHandler The collection handler.
     * @param args Arguments for command execution. Expects the key as the second argument.
     */
    @Override
    public String execute(DatabaseHandler databaseHandler, Integer userID, String[] args, BufferedReader reader) {
        var collection = databaseHandler.getCollection();

        try {
            var key = Integer.parseInt(args[1]);
            for (var entry: collection.entrySet()) {
                if (entry.getValue().key < key && entry.getValue().userID.equals(userID)) {
                    databaseHandler.removeRoute(entry.getKey());
                    break;
                }
            }
        } catch (Exception e) {
            return "Invalid key provided";
        }
        return "ok";
    }
}