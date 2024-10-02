package commands;

import handlers.CollectionHandler;
import handlers.CommandHandler;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Stack;

/**
 * Represents a command to read and execute a script from a provided file.
 */
public class ExecuteScript implements Command, Serializable {
    @Serial
    private static final long serialVersionUID = 5L;

    private static Stack<String> handledScripts = new Stack<>();

    /**
     * Get the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "execute_script";
    }

    /**
     * Get the description of the command.
     * @return The description of the command.
     */
    @Override
    public String getDescription() {
        return getName() + " file_name        -- read and execute script from provided file\n";
    }

    /**
     * Execute the command to read and execute a script from a provided file.
     *
     * @param collectionHandler The CollectionHandler to perform the operation on.
     * @param args              The arguments for the command. args[0] should be the command name, args[1] should be the file name.
     */
    @Override
    public void execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader){
    }
}
