package exceptions;

public class WrongGenderException extends RuntimeException {

    public WrongGenderException(String message) {
        super(message);
    }
}
