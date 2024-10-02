package entities;

import exceptions.InvalidParameterException;
import handlers.ReaderHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * The Location class represents a location with coordinates x, y, z, and a name.
 */
public class Location{
    private Long x; //Поле не может быть null
    private Double y;
    private Double z;
    private String name; //Строка не может быть пустой, Поле не может быть null
    private BufferedReader reader;
    /**
     * Retrieves the x parameter of the location.
     *
     * @return the x parameter
     */
    public Long getX() {
        return x;
    }
    /**
     * Retrieves the y parameter of the location.
     *
     * @return the y parameter
     */
    public Double getY() {
        return y;
    }
    /**
     * Retrieves the z parameter of the location.
     *
     * @return the z parameter
     */
    public Double getZ() {
        return z;
    }
    /**
     * Retrieves the name of the location.
     *
     * @return the name of the location
     */
    public String getName() {
        return name;
    }

    /**
     * Constructs a Location object with user input for parameters.
     */
    public Location(BufferedReader reader){
        System.out.println("Input now for parameters of Location class..");

        this.reader = reader;
        inputName();
        inputX();
        inputY();
        inputZ();
    }

    /**
     * Constructs a Location object with specified values for x, y, z, and name.
     *
     * @param x the value of the x parameter of the location
     * @param y the value of the y parameter of the location
     * @param z the value of the z parameter of the location
     * @param name the name of the location
     * @throws InvalidParameterException if x or name is null
     */
    public Location(Long x, Double y, Double z, String name) throws InvalidParameterException {
        if (x == null) {
            throw new InvalidParameterException("x cannot be null");
        }

        if (name == null) {
            throw new InvalidParameterException("name cannot be null");
        }

        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;

    }

    /**
     * Prompts user to input value for x parameter.
     */
    private void inputX(){
        System.out.print("Please input the x parameter of Location >>");

        try{
            String input = this.reader.readLine();

            if(input.isEmpty()){
                throw new InvalidParameterException("x cannot be null");
            }

            if (this.x == null){
                this.x = Long.parseLong(input);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Invalid x value, please try again...");
            inputX();
        }
    }

    /**
     * Prompts user to input value for y parameter.
     */
    private void inputY(){
        System.out.print("Please input the y parameter of Location >>");

        try{
            String input = this.reader.readLine();

            if (!input.isEmpty()){
                this.y = Double.parseDouble(input);
            }
        } catch (Exception e){
            System.out.println("Invalid y value, please try again...");
            inputY();
        }
    }
    /**
     * Prompts user to input value for z parameter.
     */
    private void inputZ(){
        System.out.print("Please input the z parameter of Location >>");

        try{
            String input = this.reader.readLine();

            if (!input.isEmpty()){
                this.z = Double.parseDouble(input);
            }
        }

        catch (Exception e){
            System.out.println("Invalid z value, please try again...");
            inputZ();
        }
    }

    /**
     * Prompts user to input value for name parameter.
     */
    private void inputName(){
        System.out.print("Please input the name parameter of Location >>");

        try{
            this.name = this.reader.readLine().strip();
            if (this.name.isEmpty()){
                throw new InvalidParameterException("name cannot be null");
            }}

        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Invalid name value, please try again...");
            inputName();
        }
    }

    public String toString(){
        return "Location " + this.name + ": x:" + this.x + " y:" + this.y + " z:" + this.z;
    }
}