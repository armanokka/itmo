package exceptions;

/**
 * The InvalidParameterException class represents an exception thrown for invalid parameters.
 */
public class InvalidParameterException extends Exception{
    /**
     * Constructs an InvalidParameterException with the specified error message.
     *
     * @param message the error message for the exception
     */
    public InvalidParameterException(String message){
        super(message);
    }
}