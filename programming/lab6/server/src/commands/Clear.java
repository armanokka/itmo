package commands;

import entities.Route;
import handlers.CollectionHandler;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Represents a command to clear the collection.
 */
public class Clear implements Command, Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
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
    public String execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader){
        Route.usedIds  = new ArrayList<>();
        Route.usedKeys  = new ArrayList<>();
        collectionHandler.updateCollection(new Hashtable<>());
        return "ok";
    }
}
