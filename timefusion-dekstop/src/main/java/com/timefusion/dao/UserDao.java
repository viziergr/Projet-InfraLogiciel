package com.timefusion.dao;

import com.timefusion.model.User;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDao extends GenericDao {

  private static final String TABLE_NAME = "User";
  private final Map<String, Class<?>> schema = new HashMap<>();

  public UserDao() {
    super(TABLE_NAME);
    defineSchema();
  }

  @Override
  protected void defineSchema() {
    try {
      schema.put("id", Integer.class);
      schema.put("first_name", String.class);
      schema.put("last_name", String.class);
      schema.put("email", String.class);
      schema.put("password", String.class);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private Object getColumnValue(String columnName, User user) {
    switch (columnName) {
      case "id":
        return user.getId();
      case "first_name":
        return user.getFirstName();
      case "last_name":
        return user.getLastName();
      case "email":
        return user.getEmail();
      case "password":
        return user.getPassword();
      default:
        return null;
    }
  }

  @Override
  protected boolean validateSchema(Object obj) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'validateSchema'"
    );
  }

  @Override
  protected int insertRecord(Object obj) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'insertRecord'"
    );
  }

  @Override
  protected int updateRecordById(Object obj) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'updateRecordById'"
    );
  }

  @Override
  protected int deleteRecordById(Object obj) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'deleteRecordById'"
    );
  }

  // public List<User> getAllUsers() {
  //     // Code to retrieve all users from the database
  //     return null;
  // }

  public Optional<User> findByEmail(String email) {
    return null;
  }
  // public Optional<User> getUserById(Long id) {
  //     // Code to retrieve a user by their ID
  //     return Optional.empty();
  // }

  // public void saveUser(User user) {
  //     // Code to save a user to the database
  // }

  // public void updateUser(User user) {
  //     // Code to update a user
  // }

  // public void deleteUser(Long id) {
  //     // Code to delete a user
  // }

  // public Optional<User> findByEmail(String email) {
  //     // Code to find a user by their email
  //     return Optional.empty();
  // }
}
