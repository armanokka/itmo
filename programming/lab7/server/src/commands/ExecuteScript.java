package commands;

import handlers.CommandHandler;
import handlers.DatabaseHandler;
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
     * @param databaseHandler The DatabaseHandler to perform the operation on.
     * @param args The arguments for the command. args[0] should be the command name, args[1] should be the file name.
     */
    @Override
    public String execute(DatabaseHandler databaseHandler, Integer userID, String[] args, BufferedReader reader){
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
                out += "\n" + CommandHandler.process(line, userID, databaseHandler, reader);
            }
        } catch (IOException e){
            out = e.toString();
        } finally {
            handledScripts.remove(scriptName);
        }
        return out;
    }
}
