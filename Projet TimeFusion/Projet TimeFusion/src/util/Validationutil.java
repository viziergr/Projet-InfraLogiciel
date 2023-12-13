package util;

public class ValidationUtil {

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }

    public static boolean isValidPassword(String password) {
        // Implement your password policy here
        return password != null && password.length() >= 8;
    }

    // Additional validation methods can be added here
}

