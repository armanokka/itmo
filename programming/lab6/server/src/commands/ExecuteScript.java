package commands;

import handlers.CollectionHandler;
import handlers.CommandHandler;
import handlers.FileHandler;
import interfaces.Command;

import java.io.*;
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
     * @param collectionHandler The CollectionHandler to perform the operation on.
     * @param args The arguments for the command. args[0] should be the command name, args[1] should be the file name.
     */
    @Override
    public String execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader){
        if (args.length < 2) {
            return "no script name provided";
        }
        String scriptName = args[1];

        if (handledScripts.contains(scriptName)){
            return "WARN: " + scriptName + " cannot be called twice, skipping...";
        }
        handledScripts.add(scriptName);

        var out = "";
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                out += "\n$ " + line;
                out += "\n" + CommandHandler.process(line, collectionHandler, reader);
            }
        } catch (IOException e){
            out = e.toString();
        } finally {
            handledScripts.remove(scriptName);
        }
        return out;
    }
}
