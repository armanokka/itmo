package commands;

import handlers.CollectionHandler;
import handlers.DatabaseHandler;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a command to count all elements from the collection which distance is less than the given value.
 */
public class CountLessThanDistance implements Command, Serializable {
    @Serial
    private static final long serialVersionUID = 4L;
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
        return "count_less_than_distance        -- count all elements from collection which distance is less than the given";
    }
    /**
     * Execute the command to count elements with distance less than the given value in the collection.
     * @param databaseHandler The DatabaseHandler to perform the operation on.
     * @param args The arguments for the command. args[0] should be the command name, args[1] should be the distance value.
     */
    @Override
    public String  execute(DatabaseHandler databaseHandler, Integer userID, String[] args, BufferedReader reader) {
        var collection = databaseHandler.getCollection();

        var out = "";
        try{
            long distance = Long.parseLong(args[1]);
            int counter = 0;

            for (var entry: collection.entrySet()) {
                if (entry.getValue().distance < distance) {
                    counter++;
                }
            }

            out += "\nNumber of Routes with distance less than" + distance + ": " + counter;
        }
        catch (NumberFormatException e){
            out = "Invalid distance provided";
        }
        return out;
    }
}
