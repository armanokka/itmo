package entities;

import exceptions.InvalidParameterException;
import handlers.ReaderHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * The Coordinates class represents a set of coordinates with x and y values.
 */
public class Coordinates {
    private Double x; //Поле не может быть null
    private Long y; //Максимальное значение поля: 781, Поле не может быть null
    private BufferedReader reader;

    /**
     * Retrieves the x parameter of the coordinates.
     *
     * @return the x parameter
     */
    public Double getX() {
        return this.x;
    }
    /**
     * Retrieves the y parameter of the coordinates.
     *
     * @return the y parameter
     */
    public Long getY() {
        return this.y;
    }

    /**
     * Constructs a Coordinates object by taking input from the user for x and y parameters.
     */
    public Coordinates(BufferedReader reader){
        System.out.println("Input now for parameters of Coordinates class..");

        this.reader = reader;
        inputX();
        inputY();
    }

    /**
     * Constructs a Coordinates object with specified x and y parameters.
     *
     * @param x the x parameter for the coordinates
     * @param y the y parameter for the coordinates
     * @throws InvalidParameterException if x or y is null or y is greater than or equal to 781
     */
    public Coordinates(Double x, Long y) throws InvalidParameterException{
        if (x == null){
            throw new InvalidParameterException("x parameter cannot be null");
        }

        if (y == null || y >= 781) {
            throw new InvalidParameterException("y parameter cannot be null");
        }

        this.x = x;
        this.y = y;
    }

    /*
     * Method for entering the value of parameter x.
     */
    private void inputX(){
        System.out.print("Please input the x parameter of Coordinates >>");

        try{
            String input = this.reader.readLine();

            if(input.isEmpty()){
                throw new InvalidParameterException("x parameter cannot be null");
            }

            this.x = Double.parseDouble(input);
        }

        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Invalid x value, please try again...");
            inputX();
        }
    }

    /*
     * Method for entering the value of parameter y.
     */
    private void inputY(){
        System.out.print("Please input the y parameter of Coordinates >>");

        try{
            String input = this.reader.readLine();

            if(input.isEmpty() || Integer.parseInt(input) >= 781){
                throw new InvalidParameterException("y parameter cannot be null or greater than 781");
            }

            this.y = Long.parseLong(input);
        }

        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Invalid y value, please try again...");
            inputY();
        }
    }

    /**
     * Retrieves a string representation of the Coordinates object.
     *
     * @return a string representation of the Coordinates object
     */
    public String toString(){
        return "Coordinates x:" + this.x + " y:" + this.y;
    }
}