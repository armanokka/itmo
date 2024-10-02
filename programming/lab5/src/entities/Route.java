package entities;

import exceptions.InvalidParameterException;
import handlers.ReaderHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The Route class represents a route with various parameters.
 */
public class Route implements Comparable<Route>{
    private static List<Integer> usedIds  = new ArrayList<>();
    private static  List<Integer> usedKeys  = new ArrayList<>();
    public Integer key;
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле может быть null
    private Location to; //Поле не может быть null
    private Double distance; //Значение поля должно быть больше 1
    private BufferedReader reader;

    /**
     * Generates a valid ID for the Route.
     *
     * @return a valid ID for the Route
     */
    private int generateValidId(){
        if (usedIds.isEmpty()) {
            return 1;
        }
        Collections.sort(usedIds);
        return usedIds.get(usedIds.size()-1)+1;
    }


    /**
     * Generates a valid key for the Route.
     *
     * @return a valid key for the Route
     */
    private Integer generateValidKey() {
        if (usedKeys.isEmpty()) {
            return 1;
        }
        Collections.sort(usedKeys);
        return usedKeys.get(usedKeys.size()-1)+1;
    }

    /**
     * Removes a used ID from the list of used IDs.
     *
     * @param id the ID to remove
     */
    public void removeUsedID(int id) {
        usedIds.remove(id);
    }

    /**
     * Fills the fields of the Route class with user input.
     */
    private void fillFields(){
        System.out.println("Input now parameters for Route class..");

        inputName();

        this.coordinates = new Coordinates(reader);
        this.creationDate = new Date();
        this.from = new Location(reader);
        this.to = new Location(reader);

        inputDistance();
    }

    /**
     * Constructs a Route object with automatically generated ID and filled fields.
     */
    public Route(){
        this.id = generateValidId();
        fillFields();
    }

    /**
     * Constructs a Route object with a specified key, if it doesn't already exist, and filled fields.
     *
     * @param key the key for the Route object
     */
    public Route(int key, BufferedReader reader) {
        this.reader = reader;
        if(usedKeys.contains(key)){
            System.out.println("Route with key " + key + " already exists");
            return;
        }

        this.key = key;
        this.id = generateValidId();
        fillFields();
    }

    /**
     * Constructs a Route object by parsing data from a CSV list.
     *
     * @param csv the CSV list containing data for the Route object
     */
    public Route(ArrayList<String> csv) throws Exception{
        System.out.println(csv);

        this.id = generateValidId();
        usedIds.add(this.id);

        try {
            this.key = Integer.parseInt(csv.get(0));
            if (usedKeys.contains(this.key)) {
                throw new InvalidParameterException("keys must be unique");
            }
            usedKeys.add(this.key);
        } catch (Exception e) {
            throw new InvalidParameterException("invalid key");
        }

        // Parsing name
        this.name = csv.get(1);
        if (this.name.isEmpty() || this.name.equals("null")) {
            throw new InvalidParameterException("name parameter cannot be null");
        }

        // Parsing creationDate
        try {
            this.creationDate = new SimpleDateFormat("yyyy-MM-dd").parse(csv.get(2));
        } catch(Exception e){
            System.out.println("Invalid creationDate parameter, moving on to next element...");
            throw new InvalidParameterException("invalid creationDate" + e.getMessage());
        }

        // Parsing coordinates
        try {
            var coordinatesX = Double.parseDouble(csv.get(3));
            var coordinatesY = Long.parseLong(csv.get(4));
            this.coordinates = new Coordinates(coordinatesX, coordinatesY);
        } catch (Exception e) {
            System.out.println("Invalid coordinates, moving on to next element...");
            throw new InvalidParameterException("invalid coordinates" + e.getMessage());
        }

        // Parsing "from" location
        try {
            var fromX = Long.parseLong(csv.get(5));
            var fromY = Double.parseDouble(csv.get(6));
            var fromZ = Double.parseDouble(csv.get(7));
            var fromName = csv.get(8);
            if (fromName.isEmpty() || fromName.equals("null")) {
                throw new InvalidParameterException("from_name parameter cannot be null");
            }
            this.from = new Location(fromX, fromY, fromZ, fromName);
        } catch (Exception e) {
            System.out.println("Invalid 'from' location, moving on to next element...");
            throw new InvalidParameterException("invalid 'from' location" + e.getMessage());
        }

        // Parsing "to" location
        try {
            var toX = Long.parseLong(csv.get(9));
            var toY = Double.parseDouble(csv.get(10));
            var toZ = Double.parseDouble(csv.get(11));
            var toName = csv.get(12);
            if (toName.isEmpty() || toName.equals("null")) {
                throw new InvalidParameterException("to_name parameter cannot be null");
            }
            this.to = new Location(toX, toY, toZ, toName);
        } catch (Exception e) {
            System.out.println("Invalid 'to' location, moving on to next element...");
            throw new InvalidParameterException("invalid 'to' location" + e.getMessage());
        }

        // Parsing distance
        try {
            this.distance = Double.parseDouble(csv.get(13));
            if (this.distance <= 1) {
                throw new InvalidParameterException("Distance must be greater than 1");
            }
        } catch (Exception e) {
            System.out.println("Invalid distance, moving on to next element...");
            throw new InvalidParameterException("invalid distance");
        }
    }

    /**
     * Input method for the name parameter of Route.
     */
    private void inputName(){
        System.out.print("Please input the name parameter of Route >>");

        try{
            String input = reader.readLine().strip();

            if (input == null || input.isEmpty()){
                throw new InvalidParameterException("name parameter cannot be null");
            }

            if (this.name == null){
                this.name = input;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Invalid name value, please try again...");
            inputName();
        }
    }

    /**
     * Input method for the distance parameter of Route.
     */
    private void inputDistance(){
        System.out.print("Please input the distance parameter of Route >>");

        String input = null;
        try {
            input = this.reader.readLine();
        } catch (Exception e) {
            System.out.println(e);
            inputDistance();
        }
        if (input == null) {
            return;
        }
        try{
            if (Long.parseLong(input) <= 1){
                throw new InvalidParameterException("distance must be greater than 1");
            }

            this.distance = Double.parseDouble(input);
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Invalid distance provided, please try again...");
            inputDistance();
        }
    }

    /**
     * Compares this Route with another Route based on distance.
     * @param r The Route to compare with.
     * @return A negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Route r){
        return Double.compare(r.getDistance(), this.distance);
    }


    /**
     * Get the distance of the Route.
     * @return The distance value.
     */
    public Double getDistance(){
        return this.distance;
    }

    /**
     * Get the ID of the Route.
     * @return The ID value.
     */
    public int getId(){
        return this.id;
    }
    /**
     * Get the key of the Route.
     * @return The key value.
     */
    public int getKey(){
        return this.key;
    }

    /**
     * Convert the Route object to a CSV format string.
     * @return The CSV formatted string representing the Route object.
     */
    public String toCSV() {
        var key = this.key.toString();
        var name = this.name.contains(",") ? '"'+this.name+'"' : this.name;
        return String.join(",", new String[]{key, name, new SimpleDateFormat("yy-MM-dd").format(this.creationDate), coordinates.getX().toString(), coordinates.getY().toString(), from.getX().toString(), from.getY().toString(), from.getZ().toString(), from.getName(), to.getX().toString(), to.getY().toString(), to.getZ().toString(), to.getName(), distance.toString()});
    }

    /**
     * Remove a specific ID from the usedIds list.
     * @param id The ID to remove.
     */
    public static void removeId(int id){
        for (var i = 0; i < usedIds.size(); i++) {
            if (usedIds.get(i) == id ) {
                usedIds.remove(i);
            }
        }

    }

    /**
     * Returns a string representation of the Route object.
     * @return A string representation of the Route object.
     */
    public String toString(){
        String output = "ID: " + this.id + "\n";
        output += "Key: " + this.key + "\n";
        output += "Name: " + this.name + "\n";
        output += this.coordinates.toString() + "\n";
        output += "Date: " + this.creationDate + "\n";
        output += "From " + this.from.toString() + "\n";
        output += "To " + this.to.toString() + "\n";
        output += "Distance: " + this.distance;

        return output;
    }
}