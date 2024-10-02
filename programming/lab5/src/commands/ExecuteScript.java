package commands;

import handlers.CollectionHandler;
import handlers.CommandHandler;
import handlers.FileHandler;
import handlers.ReaderHandler;
import interfaces.Command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Represents a command to read and execute a script from a provided file.
 */
public class ExecuteScript implements Command {
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
    public void execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader){
        if (args.length < 2) {
            System.out.println("no script name provided");
        }
        String scriptName = args[1];

        if (handledScripts.contains(scriptName)){
            System.out.println("WARN: " + scriptName + " cannot be called twice, skipping...");
            return;
        }
        handledScripts.add(scriptName);

        try {
            File file = FileHandler.process(scriptName); // Reading script file

            if (file == null) {
//                    handledScripts.remove(scriptName);
                return;
            }

            var isr = new InputStreamReader(new FileInputStream(scriptName));
            reader = new BufferedReader(isr);

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("$ " + line);
                CommandHandler.process(line, collectionHandler, reader);
                System.out.println();
            }
            isr.close();

        } catch (IOException e){
        }
        handledScripts.remove(scriptName);
    }
}
