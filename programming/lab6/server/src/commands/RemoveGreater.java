package commands;

import entities.Route;
import handlers.CollectionHandler;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.Serial;
import java.io.Serializable;
import java.util.Hashtable;

/**
 * A command implementation to remove all elements greater than a given key from the collection.
 */
public class RemoveGreater implements Command, Serializable {
    @Serial
    private static final long serialVersionUID = 13L;
    /**
     * Returns the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "remove_greater_key";
    }

    /**
     * Provides a description of the command.
     * @return A string containing the command's description.
     */
    @Override
    public String getDescription() {
        return getName() + " {key}        -- remove all elements from collection that is greater than given key\n";
    }

    /**
     * Executes the remove greater command.
     * Removes all elements from the collection that have keys greater than the provided key.
     * @param collectionHandler The collection handler.
     * @param args Arguments for command execution. Expects the key as the second argument.
     */
    @Override
    public String execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader) {
        Hashtable<Integer, Route> collection = collectionHandler.getCollection();

        /*
        Hashtable:
            key1 -> route1
                route.getKey() = key1
            key2 -> route2
         */
        try {
            var key = Integer.parseInt(args[1]);
            for (var entry: collection.entrySet()) {
                if (entry.getValue().getKey() > key) {
                    collection.remove(entry.getKey());
                    break;
                }
            }
            collectionHandler.updateCollection(collection);
        } catch (Exception e) {
            return "Invalid key provided";
        }
        return "ok";
    }
}
