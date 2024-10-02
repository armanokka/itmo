package handlers;

import entities.Route;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Receiver

/**
 * The CollectionHandler class represents a handler for a collection of Route objects.
 */
public class CollectionHandler {
    private final LocalDateTime dateCreated = LocalDateTime.now();
    private final String pathToCollection = System.getenv("FILE_NAME");
    private Hashtable<Integer, Route> collection = new Hashtable<>();
    private ArrayList<String> history = new ArrayList<String>();

    /**
     * Constructs a CollectionHandler object and initializes the collection from a file.
     *
     * @throws Exception if there are issues loading the collection from the file
     */
    public CollectionHandler() throws Exception {
        //Loading file and storing information in string
        // Code for loading and parsing the collection from a file

        if(pathToCollection == null){
            System.out.println("There is no environment variable with collection file path");
            return;
        }

        File file = FileHandler.process(pathToCollection);

        if (file != null) {
            var f = new File(pathToCollection);
            if (!f.exists()) {
                System.out.printf("File %s does not exist\n", pathToCollection);
            }
            if (!f.canRead()) {
                System.out.printf("Access to %s is denied: need right to read\n", pathToCollection);
            }
            if (!f.canWrite()) {
                System.out.printf("Access to %s is denied: need right to write\n", pathToCollection);
            }

            FileInputStream fis;
            try {
                 fis = new FileInputStream(pathToCollection);
            } catch (Exception e) {
                System.out.println("Access is denied or file doesn't exist");
                return;
            }



            InputStreamReader isr = new InputStreamReader(fis);
            String csv = "";
            int data = isr.read();

            while (data != -1) {
                csv += (char) data;
                data = isr.read();
            }
            fis.close();
            isr.close();

            var i = 0;

            for (String line: csv.split("(\\r\\n|\\r|\\n)")) {
                if (i == 0) {
                    i++;
                    continue;
                }
                var results = new ArrayList<String>();
                Matcher m = Pattern.compile("(?:^|,)(\\\"(?:[^\\\"]+\\\"\\\"|[^\\\"])*\\\")|([^,\\r\\n]*)").matcher(line);

                while (m.find()) {
                    var s = m.group();
                    if (s.trim() == "") {
                        continue;
                    }
                    if (s.startsWith(",")) {
                        s = s.substring(1);
                    }
                    results.add(s);
                }

                if (results.isEmpty()) {
                    continue;
                }

                try {
                    // TODO put(parsed_key, route)
                    this.collection.put(Integer.parseInt(results.get(0)), new Route(results));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Skipping this Route...");
                }
            }
        }
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
    public void updateCollection(Hashtable<Integer, Route> collection){
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
