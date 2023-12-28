package com.timefusion.service;

import com.timefusion.dao.UserDao;
import com.timefusion.exception.AuthenticationException;
import com.timefusion.model.User;
import com.timefusion.util.EncryptionUtil;
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
    // Retrieve the user by email
    try {
      Optional<User> user = this.userDao.findByEmail(email);

      if (user.isPresent()) {
        if (EncryptionUtil.verifyPassword(password, user.get().getPassword())) {
          return user.get();
        }
      }
    } catch (Exception e) {
      throw new AuthenticationException("Invalid email or password", e);
    }
    return null;
  }
}
