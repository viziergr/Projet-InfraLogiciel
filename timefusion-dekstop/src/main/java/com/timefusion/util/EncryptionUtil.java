package com.timefusion.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * This class provides utility methods for encrypting and verifying passwords using BCrypt.
 * BCrypt is a strong hashing algorithm that is resistant to rainbow table attacks.
 * It uses a configurable workload factor to control the time taken to hash a password,
 * making it computationally expensive for attackers to crack the hashed passwords.
 */
public class EncryptionUtil {

  /**
   * Hashing function for passwords using BCrypt.
   *
   * @param password The password to hash.
   * @return The hashed password.
   */
  public static String hashPassword(String password) {
    // Set the BCrypt workload factor
    int workload = 10;

    // Return hashed password
    return BCrypt.hashpw(password, BCrypt.gensalt(workload));
  }

  /**
   * Verify a password against its BCrypt hash.
   *
   * @param password       The password to check.
   * @param hashedPassword The hashed password to compare against.
   * @return true if the password matches the hashedPassword, false otherwise.
   */
  public static boolean verifyPassword(String password, String hashedPassword) {
    return BCrypt.checkpw(password, hashedPassword);
  }

  public static void main(String[] args) {
    String password = "@Password44";
    String hashedPassword = hashPassword(password);

    System.out.println("Password: " + password);
    System.out.println("Hashed Password: " + hashedPassword);
  }
}
