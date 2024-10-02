package commands;

import handlers.CollectionHandler;
import interfaces.Command;

import java.io.BufferedReader;
import java.util.*;

/**
 * A command implementation to print unique distances in ascending order.
 */
public class PrintFieldAscendingDistance implements Command {
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
        return getName() + "           -- show unique distances\n";
    }

    /**
     * Executes the command to print unique distances in ascending order.
     * Retrieves the collection from the collection handler, sorts it by distance, and prints unique distances in ascending order.
     * @param collectionHandler The collection handler.
     * @param args Arguments for command execution (not used for this command).
     */
    @Override
    public void execute(CollectionHandler collectionHandler, String [] args, BufferedReader reader){
        var collection = collectionHandler.getCollection();

        List<Double> distances = new ArrayList<>(new HashSet<>());
        String output = "";

        var sortedCollection = new LinkedHashMap<>();
        collection.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedCollection.put(x.getKey(), x.getValue()));


        for (var entry: sortedCollection.entrySet()) {
            output += entry.getValue().toString() + " ";
        }

        System.out.println(output);
    }
}
