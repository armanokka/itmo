package commands;

import handlers.CollectionHandler;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A command implementation to print unique distances in ascending order.
 */
public class PrintFieldAscendingDistance implements Command, Serializable {
    @Serial
    private static final long serialVersionUID = 12L;
    /**
     * Returns the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName(){
        return "print_field_ascending_distance";
    }

    /**
     * Provides a description of the command.
     * @return A string containing the command's description.
     */
    @Override
    public String getDescription(){
        return getName() + "  -- show unique distances\n";
    }

    /**
     * Executes the command to print unique distances in ascending order.
     * Retrieves the collection from the collection handler, sorts it by distance, and prints unique distances in ascending order.
     * @param collectionHandler The collection handler.
     * @param args Arguments for command execution (not used for this command).
     */
    @Override
    public String execute(CollectionHandler collectionHandler, String [] args, BufferedReader reader){
        return collectionHandler.getCollection().entrySet()
                .stream()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}
