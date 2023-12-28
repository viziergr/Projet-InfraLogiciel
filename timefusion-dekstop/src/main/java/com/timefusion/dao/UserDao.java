package com.timefusion.dao;

import com.timefusion.model.User;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDao extends GenericDao<User> {

  private static final String TABLE_NAME = "User";
  private final Map<String, Class<?>> schema = new HashMap<>();

  public UserDao() {
    super(TABLE_NAME);
    defineSchema();
  }

  @Override
  protected void defineSchema() {
    try {
      schema.put("id", Long.class);
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

  public Optional<User> findByEmail(String email) {
    return null;
  }

  @Override
  protected boolean validateSchema(User entity) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'validateSchema'"
    );
  }

  @Override
  protected int insertRecord(User entity) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'insertRecord'"
    );
  }

  @Override
  protected int updateRecordById(User entity) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'updateRecordById'"
    );
  }

  @Override
  protected int deleteRecordById(User entity) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'deleteRecordById'"
    );
  }
}
