package commands;

import handlers.CollectionHandler;
import handlers.DatabaseHandler;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a command to exit the application without saving.
 */
public class Exit implements Command, Serializable {
    @Serial
    private static final long serialVersionUID = 6L;
    /**
     * Get the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "exit";
    }

    /**
     * Get the description of the command.
     * @return The description of the command.
     */
    @Override
    public String getDescription() {
        return getName() + "                            -- exit application without saving\n";
    }

    /**
     * Execute the command to exit the application without saving.
     * @param databaseHandler The DatabaseHandler to perform the operation on.
     * @param args The arguments for the command (not used in this case).
     */
    @Override
    public String execute(DatabaseHandler databaseHandler, Integer userID, String[] args, BufferedReader reader) {
        System.exit(0);
        return "";
    }
}
