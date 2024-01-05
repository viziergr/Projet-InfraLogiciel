package com.timefusion.service;

import com.timefusion.dao.UserDao;
import com.timefusion.exception.AuthenticationException;
import com.timefusion.model.User;
import com.timefusion.util.EncryptionUtil;
import java.sql.SQLException;
import java.util.Optional;

public class AuthService {

  private UserDao userDao;

  public AuthService(UserDao userDao) {
    this.userDao = userDao;
  }

  /**
   * Authenticate a user with their email and password.
   *
   * @param email The user's email address.
   * @param password The user's password.
   * @return The id of the authenticated user.
   * @throws AuthenticationException if authentication fails.
   */
  public User authenticate(String email, String password)
    throws AuthenticationException {
    try {
      User user = this.userDao.findByEmail(email);

      if (user != null) {
        if (EncryptionUtil.verifyPassword(password, user.getPassword())) {
          return user;
        } else {
          throw new AuthenticationException("Incorrect password");
        }
      } else {
        throw new AuthenticationException("User not found");
      }
    } catch (Exception e) {
      throw new AuthenticationException("Error authenticating user", e);
    }
  }

  public static void main(String[] args)
    throws SQLException, AuthenticationException {
    AuthService authService = new AuthService(new UserDao());
    User user1 = authService.authenticate("test.robin44@gmail.com", "password");
    System.out.println(user1);
  }
}
