package commands;

import handlers.CollectionHandler;
import interfaces.Command;

import java.io.BufferedReader;

/**
 * Represents a command to exit the application without saving.
 */
public class Exit implements Command {
    /**
     * Get the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "exit";
    }

    /**
     * Get the description of the command.
     * @return The description of the command.
     */
    @Override
    public String getDescription() {
        return getName() + "                            -- exit application without saving\n";
    }

    /**
     * Execute the command to exit the application without saving.
     * @param collectionHandler The CollectionHandler to perform the operation on.
     * @param args The arguments for the command (not used in this case).
     */
    @Override
    public void execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader) {
        System.exit(0);
    }
}
