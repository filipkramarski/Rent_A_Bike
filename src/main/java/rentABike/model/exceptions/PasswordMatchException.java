package rentABike.model.exceptions;

public class PasswordMatchException extends RuntimeException{

    public PasswordMatchException() {
        super("Password and confirm password does not match");
    }
}
