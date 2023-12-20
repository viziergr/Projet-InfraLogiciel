package main.java.service;

import java.util.List;
import java.util.Optional;
import main.java.dao.UserDao;
import main.java.model.User;

public class UserService {

  private UserDao userDao;

  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  public List<User> getAllUsers() {
    return userDao.getAllUsers();
  }

  public Optional<User> getUserById(Long id) {
    return userDao.getUserById(id);
  }

  public void saveUser(User user) {
    userDao.saveUser(user);
  }

  public void updateUser(User user) {
    userDao.updateUser(user);
  }

  public void deleteUser(Long id) {
    userDao.deleteUser(id);
  }
}
