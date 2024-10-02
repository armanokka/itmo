package interfaces;

import handlers.CollectionHandler;

import java.io.BufferedReader;

/**
 * Interface representing a command in the application.
 */
public interface Command{
    String getName();
    String getDescription();
    String execute(CollectionHandler collectionHandler, String[] args, BufferedReader reader);
}