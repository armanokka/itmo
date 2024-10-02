package exceptions;

public class ForgotBackpackException extends Exception {
    public ForgotBackpackException(String personName) {
        super(personName + " forgot his backpack!");
    }
}
