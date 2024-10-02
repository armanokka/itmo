package commands;

import handlers.CollectionHandler;
import interfaces.Command;

import java.io.BufferedReader;

/**
 * A command implementation to remove an element by its key.
 */
public class RemoveByKey implements Command {
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
     * @param collectionHandler The collection handler.
     * @param args Arguments for command execution. Expects the key as the second argument.
     */
    @Override
    public void execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader) {
        var collection = collectionHandler.getCollection();
        try {
            var targetKey = Integer.parseInt(args[1]);

            collection.remove(targetKey);
            collectionHandler.updateCollection(collection);
        } catch (Exception e) {
            System.out.println("Invalid key");
        }

    }
}
