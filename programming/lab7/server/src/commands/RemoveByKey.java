package commands;

import handlers.CollectionHandler;
import handlers.DatabaseHandler;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.Serial;
import java.io.Serializable;

/**
 * A command implementation to remove an element by its key.
 */
public class RemoveByKey implements Command, Serializable {
    @Serial
    private static final long serialVersionUID = 12L;
    /**
     * Returns the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "remove_by_id";
    }

    /**
     * Provides a description of the command.
     * @return A string containing the command's description.
     */
    @Override
    public String getDescription() {
        return getName() + " id                 -- remove element with respect to id\n";
    }

    /**
     * Executes the remove by key command.
     * Removes an element from the collection based on the provided key.
     * @param databaseHandler The collection handler.
     * @param args Arguments for command execution. Expects the key as the second argument.
     */
    @Override
    public String execute(DatabaseHandler databaseHandler, Integer userID, String[] args, BufferedReader reader) {
        Integer targetKey;
        try {
            targetKey = Integer.parseInt(args[1]);
        } catch (Exception e) {
            return "Invalid key";
        }

        var targetRoute = databaseHandler.getCollection().get(targetKey);
        if (targetRoute == null) {
            return "Route with key " + targetKey + " does not exist";
        }
        if (!targetRoute.userID.equals(userID)) {
            return "Permission denied. You didn't create this route";
        }

        try {
            databaseHandler.removeRoute(targetKey);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "ok";

    }
}
