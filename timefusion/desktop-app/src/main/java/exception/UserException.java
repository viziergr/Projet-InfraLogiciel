package exception;

public class UserException extends Exception {

  public UserException(String message) {
    super(message);
  }

  public UserException(String message, Throwable cause) {
    super(message, cause);
  }
  // Additional constructors or methods as needed
}
