package com.timefusion.util;

/**
 * Utility class for validating user input.
 */
public class ValidationUtil {

  /**
   * Checks if the given email address is valid.
   *
   * @param email the email address to be validated
   * @return true if the email address is valid, false otherwise
   */
  public static boolean isValidEmail(String email) {
    String emailRegex =
      "^[a-zA-Z0-9_+&*-]+(?:\\." +
      "[a-zA-Z0-9_+&*-]+)*@" +
      "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
      "A-Z]{2,7}$";
    return email != null && email.matches(emailRegex);
  }

  /**
   * Checks if a password is valid.
   *
   * @param password the password to be validated
   * @return true if the password is valid, false otherwise
   */
  public static boolean isValidPassword(String password) {
    String passwordRegex =
      "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";
    return password != null && password.matches(passwordRegex);
  }
}
