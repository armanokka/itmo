package commands;

import handlers.CollectionHandler;
import interfaces.Command;

import java.io.BufferedReader;

/**
 * A command implementation to filter elements from a collection by distance.
 */
public class FilterByDistance implements Command {
    /**
     * Returns the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName(){
        return "filter_by_distance";
    }

    /**
     * Provides a description of the command.
     * @return A string containing the command's description.
     */
    @Override
    public String getDescription(){
        return getName() + " filter_by_distance      -- print all elements from collection which distance is equal to the given\n";
    }

    /**
     * Executes the filter operation based on the given distance.
     * Prints elements from the collection whose distance is equal to the provided distance.
     * @param collectionHandler The collection handler managing the collection.
     * @param args The arguments for the command execution. Expects the distance as the second argument.
     */
    @Override
    public void execute(CollectionHandler collectionHandler, String [] args, BufferedReader reader){
        var collection = collectionHandler.getCollection();

        try{
            long distance = Long.parseLong(args[1]);
            int counter = 0;

            for (var entry: collection.entrySet()) {
                if (entry.getValue().getDistance() == distance) {
                    System.out.println(entry.getValue());
                    counter++;
                }
            }
            System.out.println("Total number of elements: " + counter);
        }
        catch (NumberFormatException e){
            System.out.println("Invalid distance provided");
        }
    }

}
