package interfaces;

import handlers.CollectionHandler;
import handlers.DatabaseHandler;

import java.io.BufferedReader;

/**
 * Interface representing a command in the application.
 */
public interface Command{
    String getName();
    String getDescription();
    String execute(DatabaseHandler databaseHandler, Integer userID, String[] args, BufferedReader reader);
}