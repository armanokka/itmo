package handlers;

import entities.Route;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;

// Receiver

/**
 * The CollectionHandler class represents a handler for a collection of Route objects.
 */
public class CollectionHandler {
    private final LocalDateTime dateCreated = LocalDateTime.now();
    private final String pathToCollection = System.getenv("FILE_NAME");
    private Hashtable<Integer, Route> collection = new Hashtable<>();
    private ArrayList<String> history = new ArrayList<String>();

    private Connection conn;

    /**
     * Constructs a CollectionHandler object and initializes the collection from a file.
     *
     * @throws Exception if there are issues loading the collection from the file
     */
    public CollectionHandler() {

    }

    /**
     * Retrieves information about the collection.
     *
     * @return a string containing information about the collection
     */
    public String info(){
        // Code for generating information about the collection
        String output = "Collection " + this.collection.getClass().getSimpleName();
        output += " containing " + this.collection.size() + " of object Route. \n";
        output += "Collection created on " + dateCreated + ".\n";
        output += "Collection stored at " + pathToCollection + ".";

        return output;
    }

    /**
     * Updates the collection with a new Hashtable of Route objects.
     *
     * @param collection the new collection to update
     */
    public void updateCollection(Hashtable<Integer, Route> collection) {
        this.collection = collection;
    }

    /**
     * Retrieves the current collection of Route objects.
     *
     * @return the current collection
     */
    public Hashtable<Integer, Route> getCollection() {
        return this.collection;
    }

    /**
     * Saves a command to the command history.
     *
     * @param command the command to save
     */
    public void saveCommandHistory(String command) {
        this.history.add(command);
        var size = this.history.size();
        if (size > 6) {
            this.history.remove(0);
        }
    }

    /**
     * Retrieves the command history.
     *
     * @return the command history
     */
    public ArrayList<String> getCommandHistory() {
        return this.history;
    }
}
