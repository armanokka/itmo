package commands;

import handlers.CollectionHandler;
import interfaces.Command;

import java.io.BufferedReader;

/**
 * A command implementation to display information about the collection.
 */
public class Info implements Command {
    /**
     * Returns the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "info";
    }

    /**
     * Provides a description of the command.
     * @return A string containing the command's description.
     */
    @Override
    public String getDescription() {
        return getName() + "                            -- show information about collection (type, initialization date, number of elements)\n";
    }

    /**
     * Executes the info command.
     * Displays information about the collection, including its type, initialization date, and number of elements.
     * @param collectionHandler The collection handler.
     * @param args Arguments for command execution (not used for info command).
     */
    @Override
    public void execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader) {
        String info = collectionHandler.info();

        System.out.println(info);
    }
}
