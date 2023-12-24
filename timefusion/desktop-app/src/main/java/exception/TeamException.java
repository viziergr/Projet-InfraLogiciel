package main.java.exception;

public class TeamException extends Exception {

  public TeamException(String message) {
    super(message);
  }

  public TeamException(String message, Throwable cause) {
    super(message, cause);
  }
  // Additional constructors or methods as needed
}
