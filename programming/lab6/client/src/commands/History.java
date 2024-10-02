package commands;

import handlers.CollectionHandler;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.Serial;
import java.io.Serializable;

/**
 * A command implementation to display the history of executed commands.
 */
public class History implements Command, Serializable {
    @Serial
    private static final long serialVersionUID = 9L;
    /**
     * Returns the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "history";
    }
    /**
     * Provides a description of the command.
     * @return A string containing the command's description.
     */
    @Override
    public String getDescription() {
        return this.getName() + "                         -- show last 6 distances\n";
    }

    /**
     * Executes the history command.
     * Displays the last 6 executed commands.
     * @param collectionHandler The collection handler.
     * @param args Arguments for command execution (not used for history command).
     */
    @Override
    public void execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader) {
        var history = collectionHandler.getCommandHistory();
        for (String cmd: history) {
            System.out.println(cmd);
        }
    }
}
