package exception;

public class EventException extends Exception {

    public EventException(String message) {
        super(message);
    }

    public EventException(String message, Throwable cause) {
        super(message, cause);
    }

    // Additional constructors or methods as needed
}

