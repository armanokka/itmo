package commands;

import handlers.CollectionHandler;
import handlers.PackageParser;
import interfaces.Command;

import java.io.BufferedReader;

/**
 * A command implementation to display information about available commands.
 */
public class Help implements Command {
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
     * @param collectionHandler The collection handler.
     * @param args Arguments for command execution (not used for help command).
     */
    @Override
    public void execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader){
        var commands = new String[]{"help", "info", "show", "insert", "update", "remove_key", "clear", "save", "execute_script", "exit", "history", "remove_greater_key", "remove_lower_key", "count_less_than_distance", "filter_by_distance", "print_field_ascending_distance"};

        for(String commandName: commands){
            var cmd = PackageParser.getCommand(commandName);
            System.out.print(cmd.getDescription());
        }
    }
}