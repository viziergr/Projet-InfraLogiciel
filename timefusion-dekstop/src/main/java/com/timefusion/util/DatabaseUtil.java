package com.timefusion.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * A utility class for interacting with the MySQL database.
 */
public class DatabaseUtil {

  private static final String DEFAULT_DATABASE_URL =
    "jdbc:mysql://localhost:3306/your_database"; // [TODO]: Replace with the database URL
  private static final String DEFAULT_DATABASE_USER = "your_username"; // [TODO]: Replace with the database user
  private static final String DEFAULT_DATABASE_PASSWORD = "your_password"; // [TODO]: Replace with the database password

  private String databaseUrl;
  private String databaseUser;
  private String databasePassword;

  public DatabaseUtil() {
    this(
      DEFAULT_DATABASE_URL,
      DEFAULT_DATABASE_USER,
      DEFAULT_DATABASE_PASSWORD
    );
  }

  public DatabaseUtil(
    String databaseUrl,
    String databaseUser,
    String databasePassword
  ) {
    this.databaseUrl = databaseUrl;
    this.databaseUser = databaseUser;
    this.databasePassword = databasePassword;
  }

  /**
   * Establishes a database connection.
   *
   * @return The database connection.
   * @throws SQLException If a database access error occurs.
   */
  public Connection getConnection() throws SQLException {
    try {
      // Load the MySQL driver
      Class.forName("com.mysql.cj.jdbc.Driver");

      // Establish the connection
      return DriverManager.getConnection(
        databaseUrl,
        databaseUser,
        databasePassword
      );
    } catch (ClassNotFoundException e) {
      throw new SQLException("Failed to load MySQL driver.", e);
    }
  }

  /**
   * Closes the given database connection.
   *
   * @param connection The database connection to close.
   */
  public static void closeConnection(Connection connection) {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    } catch (SQLException e) {
      // Log or handle the exception as needed
      e.printStackTrace();
    }
  }

  /**
   * Executes an update (INSERT, UPDATE, DELETE) SQL statement.
   *
   * @param sql The SQL statement to execute.
   * @return The number of rows affected by the update.
   * @throws SQLException If a database access error occurs.
   */
  public int executeUpdate(String sql) throws SQLException {
    try (
      Connection connection = getConnection();
      Statement statement = connection.createStatement()
    ) {
      return statement.executeUpdate(sql);
    }
  }

  /**
   * Executes a query and returns the result set.
   *
   * @param sql The SQL query to execute.
   * @return The result set containing the query results.
   * @throws SQLException If a database access error occurs.
   */
  public ResultSet executeQuery(String sql) throws SQLException {
    try (
      Connection connection = getConnection();
      Statement statement = connection.createStatement()
    ) {
      return statement.executeQuery(sql);
    }
  }

  /**
   * Inserts a record into the specified table with the given column values.
   *
   * @param tableName     the name of the table to insert the record into
   * @param columnValues  a map containing the column names and their corresponding values
   * @return the number of rows affected by the insert operation
   * @throws SQLException if a database access error occurs
   * @throws IllegalArgumentException if the columnValues map is empty or if the tableName is empty
   */
  public int insertRecord(String tableName, Map<String, Object> columnValues)
    throws SQLException {
    if (columnValues.isEmpty()) {
      throw new IllegalArgumentException("Column values map is empty.");
    }
    if (tableName.isEmpty()) {
      throw new IllegalArgumentException("Table name is empty.");
    }

    StringBuilder insertSql = new StringBuilder(
      "INSERT INTO " + tableName + " ("
    );
    StringBuilder valuesPlaceholder = new StringBuilder(") VALUES (");

    for (String columnName : columnValues.keySet()) {
      insertSql.append(columnName).append(",");
      valuesPlaceholder.append("?,");
    }

    insertSql.deleteCharAt(insertSql.length() - 1);
    valuesPlaceholder.deleteCharAt(valuesPlaceholder.length() - 1);

    insertSql.append(valuesPlaceholder).append(")");

    try (
      Connection connection = getConnection();
      PreparedStatement statement = connection.prepareStatement(
        insertSql.toString()
      )
    ) {
      int parameterIndex = 1;
      for (Object value : columnValues.values()) {
        statement.setObject(parameterIndex++, value);
      }

      return statement.executeUpdate();
    }
  }

  /**
   * Updates a record in the specified table based on the record ID.
   *
   * @param tableName       The name of the table to update the record in.
   * @param idColumnName    The name of the column representing the record ID.
   * @param recordId        The unique identifier of the record to be updated.
   * @param columnValues    A map where keys are column names, and values are the new values
   *                        to be set for the respective columns.
   * @return The number of rows affected by the update (0 or 1).
   * @throws SQLException   If a database access error occurs.
   */
  public int updateRecordById(
    String tableName,
    String idColumnName,
    long recordId,
    Map<String, Object> columnValues
  ) throws SQLException {
    if (tableName.isEmpty()) {
      throw new IllegalArgumentException("Table name is empty.");
    }
    if (idColumnName.isEmpty()) {
      throw new IllegalArgumentException("ID column name is empty.");
    }
    if (columnValues.isEmpty()) {
      throw new IllegalArgumentException("Column values map is empty.");
    }

    StringBuilder updateSql = new StringBuilder(
      "UPDATE " + tableName + " SET "
    );

    for (String columnName : columnValues.keySet()) {
      updateSql.append(columnName).append("=?,");
    }

    updateSql.deleteCharAt(updateSql.length() - 1);

    updateSql.append(" WHERE ").append(idColumnName).append(" = ?");

    try (
      Connection connection = getConnection();
      PreparedStatement statement = connection.prepareStatement(
        updateSql.toString()
      )
    ) {
      int parameterIndex = 1;
      for (Object value : columnValues.values()) {
        statement.setObject(parameterIndex++, value);
      }

      statement.setLong(parameterIndex, recordId);

      return statement.executeUpdate();
    }
  }

  /**
   * Deletes a record from the specified table based on the record ID.
   *
   * @param tableName       The name of the table from which to delete the record.
   * @param idColumnName    The name of the column representing the record ID.
   * @param recordId        The unique identifier of the record to be deleted.
   * @return The number of rows affected by the deletion (0 or 1).
   * @throws SQLException   If a database access error occurs.
   */
  public int deleteRecordById(
    String tableName,
    String idColumnName,
    long recordId
  ) throws SQLException {
    if (tableName.isEmpty()) {
      throw new IllegalArgumentException("Table name is empty.");
    }
    if (idColumnName.isEmpty()) {
      throw new IllegalArgumentException("ID column name is empty.");
    }

    String deleteSql =
      "DELETE FROM " + tableName + " WHERE " + idColumnName + " = ?";

    try (
      Connection connection = getConnection();
      PreparedStatement statement = connection.prepareStatement(deleteSql)
    ) {
      statement.setLong(1, recordId);

      return statement.executeUpdate();
    }
  }

  /**
   * Retrieve records from the database based on dynamic criteria.
   *
   * @param tableName   The name of the database table.
   * @param criteriaMap A map containing field-value pairs for the criteria.
   * @return A ResultSet containing the matching records.
   * @throws SQLException If a database access error occurs.
   */
  public ResultSet retrieveRecordsWithCriteria(
    String tableName,
    Map<String, Object> criteriaMap
  ) throws SQLException {
    if (criteriaMap == null || criteriaMap.isEmpty()) {
      throw new IllegalArgumentException(
        "Criteria map must not be null or empty."
      );
    }

    StringBuilder sql = new StringBuilder("SELECT * FROM ")
      .append(tableName)
      .append(" WHERE ");

    for (Map.Entry<String, Object> entry : criteriaMap.entrySet()) {
      sql.append(entry.getKey()).append(" = ? AND ");
    }

    // Remove the trailing "AND"
    sql.setLength(sql.length() - 5);

    // Prepare and execute the SQL query
    try (
      PreparedStatement statement = getConnection()
        .prepareStatement(sql.toString())
    ) {
      int parameterIndex = 1;

      for (Object value : criteriaMap.values()) {
        statement.setObject(parameterIndex++, value);
      }

      return statement.executeQuery();
    }
  }

  public static void main(String[] args) {
    try {
      // Load the JDBC driver
      Class.forName("com.mysql.cj.jdbc.Driver");

      System.out.println("MySQL JDBC driver loaded successfully.");
    } catch (ClassNotFoundException e) {
      e.printStackTrace(); // Handle the exception appropriately
    }
  }
}
