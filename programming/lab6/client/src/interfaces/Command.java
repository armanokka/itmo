package interfaces;

import handlers.CollectionHandler;

import java.io.BufferedReader;

/**
 * Interface representing a command in the application.
 */
public interface Command{
    String getName();
    String getDescription();
    void execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader);
}