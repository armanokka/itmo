package commands;

import handlers.CollectionHandler;
import handlers.DatabaseHandler;
import handlers.PackageParser;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.Serial;
import java.io.Serializable;

/**
 * A command implementation to display information about available commands.
 */
public class Help implements Command, Serializable {
    @Serial
    private static final long serialVersionUID = 8L;
    /**
     * Returns the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName(){
        return "help";
    }

    /**
     * Provides a description of the command.
     * @return A string containing the command's description.
     */
    @Override
    public String getDescription(){
        return getName() + "                            -- show information about available commands\n";
    }

    /**
     * Executes the help command.
     * Displays information about all available commands.
     * @param databaseHandler The collection handler.
     * @param args Arguments for command execution (not used for help command).
     */
    @Override
    public String execute(DatabaseHandler databaseHandler, Integer userID, String[] args, BufferedReader reader){
        var commands = new String[]{"help", "info", "show", "insert", "update", "remove_key", "clear", "save", "execute_script", "exit", "history", "remove_greater_key", "remove_lower_key", "count_less_than_distance", "filter_by_distance", "print_field_ascending_distance"};
        var out = "";
        for(String commandName: commands){
            var cmd = PackageParser.getCommand(commandName);
            out += cmd.getDescription();
        }
        return out;
    }
}