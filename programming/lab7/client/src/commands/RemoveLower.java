package commands;

import entities.Route;
import handlers.CollectionHandler;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

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
        return "remove_lower";
    }

    /**
     * Provides a description of the command.
     * @return A string containing the command's description.
     */
    @Override
    public String getDescription() {
        return getName() + " {element}          -- remove all elements from collection that is lower than given\n";
    }

    /**
     * Executes the remove lower command.
     * Removes all elements from the collection that have keys lower than the provided key.
     * @param collectionHandler The collection handler.
     * @param args Arguments for command execution. Expects the key as the second argument.
     */
    @Override
    public void execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader) {
        Hashtable<Integer, Route> collection = collectionHandler.getCollection();

        try {
            var key = Integer.parseInt(args[1]);
            for (var entry: collection.entrySet()) {
                if (entry.getValue().key < key) {
                    collection.remove(entry.getKey());
                    break;
                }
            }
            collectionHandler.updateCollection(collection);
        } catch (Exception e) {
            System.out.println("Invalid key provided");
        }
    }
}