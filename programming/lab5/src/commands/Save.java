package commands;

import handlers.CollectionHandler;
import interfaces.Command;

import java.io.*;

/**
 * A command implementation to save the collection to a file.
 */
public class Save implements Command {
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
    public void execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader) {
        var collection = collectionHandler.getCollection();

        String savePath = System.getenv("FILE_NAME");

        if(savePath == null){
            System.out.println("There is no environment variable with collection file path");
            return;
        }

        String output = "key,name,creationDate,coordinate_x,coordinate_y,from_x,from_y,from_z,from_name,to_x,to_y,to_z,to_name,distance\n";

        for (var entry: collection.entrySet()) {
            output += entry.getValue().toCSV() + "\n";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(savePath))) {
            writer.write(output);
        } catch (IOException e) {
            System.err.println("Error when saving file: " + e.getMessage());
        }

    }
}
