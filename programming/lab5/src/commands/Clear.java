package commands;

import handlers.CollectionHandler;
import interfaces.Command;

import java.io.BufferedReader;
import java.util.Hashtable;

/**
 * Represents a command to clear the collection.
 */
public class Clear implements Command {
    /**
     * Get the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "clear";
    }

    /**
     * Get the description of the command.
     * @return The description of the command.
     */
    @Override
    public String getDescription() {
        return getName() + "                           -- clear the whole collection\n";
    }

    /**
     * Execute the clear command to update the collection.
     * @param collectionHandler The CollectionHandler to perform the operation on.
     * @param args The arguments for the command (not used in this case).
     */
    @Override
    public void execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader){
        collectionHandler.updateCollection(new Hashtable<>());
    }
}
