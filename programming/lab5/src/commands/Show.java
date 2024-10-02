package commands;

import handlers.CollectionHandler;
import interfaces.Command;

import java.io.BufferedReader;

/**
 * A command implementation to display all elements of the collection.
 */
public class Show implements Command {
    /**
     * Returns the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "show";
    }

    /**
     * Provides a description of the command.
     * @return A string containing the command's description.
     */
    @Override
    public String getDescription() {
        return getName() + "                            -- show all elements of collection in String representation\n";
    }

    /**
     * Executes the show command.
     * Displays all elements of the collection in string representation.
     * @param collectionHandler The collection handler.
     * @param args Arguments for command execution (not used for show command).
     */
    @Override
    public void execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader) {
        var collection = collectionHandler.getCollection();

        if (collection.isEmpty()){
            System.out.println("Collection is empty!");
        }
        for (var entry : collection.entrySet()) {
            System.out.println(entry.getValue());
            System.out.println("");
        }
    }
}
