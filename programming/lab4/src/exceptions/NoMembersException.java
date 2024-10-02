package exceptions;

public class NoMembersException extends RuntimeException {
    public NoMembersException() {
        super("Not enough members to start the expedition!");
    }
}
