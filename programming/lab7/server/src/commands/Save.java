package commands;

import entities.Route;
import handlers.CollectionHandler;
import handlers.DatabaseHandler;
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
     * @param databaseHandler The collection handler.
     * @param args Arguments for command execution (not used for save command).
     */
    @Override
    public String execute(DatabaseHandler databaseHandler, Integer userID, String[] args, BufferedReader reader) {
        return "ok"; // The collection automatically gets saved in databased when it's updated
    }
}
