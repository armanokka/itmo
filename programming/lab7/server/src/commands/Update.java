package commands;

import entities.Route;
import handlers.CollectionHandler;
import handlers.DatabaseHandler;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.Serial;
import java.io.Serializable;

/**
 * A command implementation to update a collection element by its ID.
 */
public class Update implements Command, Serializable {
    @Serial
    private static final long serialVersionUID = 17L;
    /**
     * Returns the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "update";
    }

    /**
     * Provides a description of the command.
     * @return A string containing the command's description.
     */
    @Override
    public String getDescription() {
        return getName() + " id {element}             -- update collection element with respect to id\n";
    }

    /**
     * Executes the update command.
     * Updates a collection element identified by its ID with the provided element.
     * @param databaseHandler The collection handler.
     * @param args Arguments for command execution. Expects the ID and the new element as the second and third arguments, respectively.
     */
    @Override
    public String execute(DatabaseHandler databaseHandler, Integer userID, String[] args, BufferedReader reader) {
        if (args.length != 3) {
            return "invalid number of arguments";
        }
        int id = Integer.parseInt(args[2]);

        var collection = databaseHandler.getCollection();

        var key = -1;
        for (var entry: collection.entrySet()) {
            System.out.println(entry.getValue().id);
            if (entry.getValue().id == id) {
                key = entry.getValue().key;
                break;
            }
        }
        if (key == -1) {
            return "element does not exist";
        }

        if (!databaseHandler.getCollection().get(key).userID.equals(userID)) {
            return "Permission denied. You didn't create this route";
        }
        try {
            databaseHandler.removeRoute(key);
            new Insert().execute(databaseHandler, userID, new String[]{"insert", args[2]}, reader);
            return "";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
