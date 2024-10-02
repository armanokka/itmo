package entities;

import exceptions.InvalidParameterException;

import java.io.BufferedReader;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * The Route class represents a route with various parameters.
 */
public class Route implements Comparable<Route>, Serializable {
    @Serial
    private static final long serialVersionUID = 18L;
    public Integer key;
    public int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    public String name; //Поле не может быть null, Строка не может быть пустой
    public Coordinates coordinates; //Поле не может быть null
    public Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    public Location from; //Поле может быть null
    public Location to; //Поле не может быть null
    public Double distance; //Значение поля должно быть больше 1
    public Integer userID;
    public transient BufferedReader reader;

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
     * Constructs a Route object with a specified key, if it doesn't already exist, and filled fields.
     *
     * @param key the key for the Route object
     */
    public Route(int key, BufferedReader reader) {
        this.reader = reader;
        this.key = key;
        this.id = key;
        fillFields();
    }

    public Route() {
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
        return Double.compare(r.distance, this.distance);
    }

    /**
     * Returns a string representation of the Route object.
     * @return A string representation of the Route object.
     */
    public String toString(){
        String output = "ID: " + this.id +
        "\nKey: " + this.key +
        "\nName: " + this.name +
        "\n" + this.coordinates.toString() +
        "\nDate: " + this.creationDate +
        "\nFrom " + this.from.toString() +
        "\nTo " + this.to.toString() +
        "\nDistance: " + this.distance +
        "\nOwner user ID: " + this.userID;
        return output;
    }
}