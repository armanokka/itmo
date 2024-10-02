package commands;

import entities.Route;
import handlers.CollectionHandler;
import interfaces.Command;

import java.io.BufferedReader;

/**
 * A command implementation to insert a new element into the collection.
 */
public class Insert implements Command {
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
        return "insert      -- create a new element with the given key";
    }

    /**
     * Executes the insert command.
     * Creates a new element with the given key and inserts it into the collection.
     * @param collectionHandler The collection handler.
     * @param args Arguments for command execution. Expects the key as the second argument.
     */
    @Override
    public void execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader) {
        if (args.length != 2) {
            System.out.println("invalid number of arguments");
            return;
        }
        var collection = collectionHandler.getCollection();

        try {
            var key = Integer.parseInt(args[1]);
            var r = new Route(key, reader);
            collection.put(key, r);
            collectionHandler.updateCollection(collection);
        } catch (Exception e) {
            System.out.println("invalid key provided. Must be integer");
        }
    }
}
