package commands;

import handlers.CollectionHandler;
import handlers.DatabaseHandler;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.Serial;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * A command implementation to filter elements from a collection by distance.
 */
public class FilterByDistance implements Command, Serializable {
    @Serial
    private static final long serialVersionUID = 7L;
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
     * @param databaseHandler The collection handler managing the collection.
     * @param args The arguments for the command execution. Expects the distance as the second argument.
     */
    @Override
    public String execute(DatabaseHandler databaseHandler, Integer userID, String [] args, BufferedReader reader){
        try{
            long distance = Long.parseLong(args[1]);
            return databaseHandler.getCollection().entrySet().stream()
                    .filter(item->item.getValue().distance == distance)
                    .map(Object::toString).collect(Collectors.joining("\n"));
        }
        catch (NumberFormatException e){
            return "Invalid distance provided";
        }
    }

}
