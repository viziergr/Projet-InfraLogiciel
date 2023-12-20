package main.java.config;

// A utiliser au détriment de ConfigUtil et config.properties

public class ApplicationConfig {

  // Database configuration
  public static final String DATABASE_URL = "jdbc:your_database_url";
  public static final String DATABASE_USER = "your_username";
  public static final String DATABASE_PASSWORD = "your_password";

  // Email configuration for notifications
  public static final String EMAIL_HOST = "smtp.your_email_host.com";
  public static final String EMAIL_PORT = "587";
  public static final String EMAIL_USERNAME = "your_email_username";
  public static final String EMAIL_PASSWORD = "your_email_password";

  // Other application settings
  public static final int SESSION_TIMEOUT = 30; // in minutes
  // Add more configuration settings as needed
}
