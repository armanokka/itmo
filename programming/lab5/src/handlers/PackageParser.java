package handlers;

import commands.*;
import interfaces.Command;

import java.util.HashMap;

/**
 * The PackageParser class provides methods for parsing and retrieving commands.
 */
public class PackageParser {
    /**
     * Retrieves the Command object based on the specified commandName.
     *
     * @param commandName the name of the command to retrieve
     * @return the Command object corresponding to the commandName, or null if the command is not recognized
     */
    public static Command getCommand(String commandName){
        // Retrieve the Command object based on the specified commandName
        // Return the Command object or null if the command is not recognized
        Command command;
        switch (commandName.toLowerCase()) {
            case "clear" -> command = new Clear();
            case "help" -> command = new Help();
            case "info" -> command = new Info();
            case "show" -> command = new Show();
            case "insert" -> command = new Insert();
            case "update" -> command = new Update();
            case "remove_key" -> command = new RemoveByKey();
            case "save" -> command = new Save();
            case "execute_script" -> command = new ExecuteScript();
            case "exit" -> command = new Exit();
            case "history" -> command = new History();
            case "remove_greater_key" -> command = new RemoveGreater();
            case "remove_lower_key" -> command = new RemoveLower();
            case "count_less_than_distance" -> command = new CountLessThanDistance();
            case "filter_by_distance" -> command = new FilterByDistance();
            case "print_field_ascending_distance" -> command = new PrintFieldAscendingDistance();
            default -> command = null;
        }
        return command;
    }
}
