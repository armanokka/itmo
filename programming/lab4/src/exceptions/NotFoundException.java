package exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(Object object) {
        super(object + " not found in the backpack!");
    }
}
