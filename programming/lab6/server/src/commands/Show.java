package commands;

import entities.Route;
import handlers.CollectionHandler;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * A command implementation to display all elements of the collection.
 */
public class Show implements Command, Serializable {
    @Serial
    private static final long serialVersionUID = 16L;
    /**
     * Returns the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "show";
    }

    /**
     * Provides a description of the command.
     * @return A string containing the command's description.
     */
    @Override
    public String getDescription() {
        return getName() + "                            -- show all elements of collection in String representation\n";
    }

    /**
     * Executes the show command.
     * Displays all elements of the collection in string representation.
     * @param collectionHandler The collection handler.
     * @param args Arguments for command execution (not used for show command).
     */
    @Override
    public String execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader) {
        var collection = collectionHandler.getCollection();

        if (collection.isEmpty()){
            return "Collection is empty!";
        }
        var out = "";

        List<Map.Entry<Integer, Route>> list = new ArrayList<>(collection.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Route>>() {
            @Override
            public int compare(Map.Entry<Integer, Route> a, Map.Entry<Integer, Route> b) {
                var coordA = a.getValue().getCoordinates();
                var coordB = b.getValue().getCoordinates();
                if (!coordA.getX().equals(coordB.getX())) { // x1 not equals x2
                    return coordA.getX().compareTo(coordB.getX());
                }
                return coordA.getY().compareTo(coordB.getY());
            }
        });

        for (var entry : list) {
            out +=  entry.getValue() + "\n\n";
        }
        return out.trim();
    }
}
