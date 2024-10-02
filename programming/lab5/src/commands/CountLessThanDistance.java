package commands;

import handlers.CollectionHandler;
import interfaces.Command;

import java.io.BufferedReader;

/**
 * Represents a command to count all elements from the collection which distance is less than the given value.
 */
public class CountLessThanDistance implements Command {
    /**
     * Get the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "count_less_than_distance";
    }
    /**
     * Get the description of the command.
     * @return The description of the command.
     */
    @Override
    public String getDescription() {
        return "count_less_than_distance      -- count all elements from collection which distance is less than the given";
    }
    /**
     * Execute the command to count elements with distance less than the given value in the collection.
     * @param collectionHandler The CollectionHandler to perform the operation on.
     * @param args The arguments for the command. args[0] should be the command name, args[1] should be the distance value.
     */
    @Override
    public void execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader) {
        var collection = collectionHandler.getCollection();

        try{
            long distance = Long.parseLong(args[1]);
            int counter = 0;

            for (var entry: collection.entrySet()) {
                if (entry.getValue().getDistance() < distance) {
                    counter++;
                }
            }

            String output = "Number of Routes with distance less than" + distance + ": " + counter;
            System.out.println(output);
        }
        catch (NumberFormatException e){
            System.out.println("Invalid distance provided");
        }
    }
}
