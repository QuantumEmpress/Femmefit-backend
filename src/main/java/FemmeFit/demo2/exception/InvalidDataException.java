
package FemmeFit.demo2.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException() {
        super("Invalid data provided");
    }

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataException(Throwable cause) {
        super(cause);
    }
}