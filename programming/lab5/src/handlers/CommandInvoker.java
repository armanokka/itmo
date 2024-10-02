package handlers;

import interfaces.Command;

import java.io.BufferedReader;

/**
 * The CommandInvoker class represents an invoker for executing commands.
 */
public class CommandInvoker {
    private Command command;

    /**
     * Sets the command to be executed.
     *
     * @param command the command to set
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * Executes the set command using the provided CollectionHandler and arguments.
     *
     * @param collectionHandler the CollectionHandler to use for executing the command
     * @param args the arguments for the command
     */
    public void executeCommand(CollectionHandler collectionHandler, String[] args, BufferedReader reader) {
        command.execute(collectionHandler, args, reader);
    }
}
