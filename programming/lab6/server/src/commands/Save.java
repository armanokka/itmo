package commands;

import entities.Route;
import handlers.CollectionHandler;
import interfaces.Command;

import java.io.*;
import java.util.*;

/**
 * A command implementation to save the collection to a file.
 */
public class Save implements Command, Serializable {
    @Serial
    private static final long serialVersionUID = 15L;
    /**
     * Returns the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName(){
        return "save";
    }

    /**
     * Provides a description of the command.
     * @return A string containing the command's description.
     */
    @Override
    public String getDescription(){
        return getName() + "                            -- save collection to file\n";
    }

    /**
     * Executes the save command.
     * Saves the collection to a file specified by the FILE_NAME environment variable.
     * @param collectionHandler The collection handler.
     * @param args Arguments for command execution (not used for save command).
     */
    @Override
    public String execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader) {
        var collection = collectionHandler.getCollection();

        String savePath = System.getenv("FILE_NAME");

        if(savePath == null){
            return "There is no environment variable with collection file path";
        }

        String output = "key,name,creationDate,coordinate_x,coordinate_y,from_x,from_y,from_z,from_name,to_x,to_y,to_z,to_name,distance\n";

        List<Map.Entry<Integer, Route>> list = new ArrayList<>(collection.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Route>>() {
            @Override
            public int compare(Map.Entry<Integer, Route> a, Map.Entry<Integer, Route> b) {
                var coordA = a.getValue().getCoordinates();
                var coordB = b.getValue().getCoordinates();
                if (!coordA.getX().equals(coordB.getX())) { // x not equals
                    return coordA.getX().compareTo(coordB.getX());
                }
                return coordA.getY().compareTo(coordB.getY());
            }
        });

        for (var entry: list) {
            output += entry.getValue().toCSV() + "\n";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(savePath))) {
            writer.write(output);
        } catch (IOException e) {
            return "Error when saving file: " + e.getMessage();
        }
        return "ok";
    }
}
