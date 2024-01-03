package com.timefusion.dao;

import com.timefusion.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

  /**
   * Retrieves a record from the table.
   *
   * @param tableName the name of the database table
   * @param criteriaMap the map of criteria to be used in the query
   * @return the record retrieved from the table
   * @throws IllegalArgumentException if the provided table name or criteria map is null or empty
   */
  @Override
  protected User retrieveRecord(
    String tableName,
    Map<String, Object> criteriaMap
  ) {
    if (tableName == null || criteriaMap == null || criteriaMap.isEmpty()) {
      throw new IllegalArgumentException(
        "Invalid arguments for retrieveRecord"
      );
    }

    try {
      ResultSet resultSet = super.databaseUtil.retrieveRecordsWithCriteria(
        tableName,
        criteriaMap
      );

      if (resultSet.next()) {
        // Map the result set to a User object
        return mapResultSetToUser(resultSet);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  // Map the result set to a User object
  private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
    User user = new User(
      resultSet.getLong("id"),
      resultSet.getString("first_name"),
      resultSet.getString("last_name"),
      resultSet.getString("email"),
      resultSet.getString("password")
    );
    return user;
  }

  //Create a main fucntion to test to retrieve a record from the table
  public static void main(String[] args) {
    UserDao userDao = new UserDao();
    Map<String, Object> criteriaMap = new HashMap<>();
    criteriaMap.put("email", "corentin.robin@reseau.eseo.fr");
    User user = userDao.retrieveRecord(TABLE_NAME, criteriaMap);
    System.out.println(user.toString());
  }
}
