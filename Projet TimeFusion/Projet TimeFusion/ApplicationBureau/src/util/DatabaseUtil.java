package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * Examples and Use Cases for the DatabaseUtil Class:
 *
 * 1. Establishing a Database Connection:
 *
 *    ```java
 *    DatabaseUtil util = new DatabaseUtil();
 *
 *    try (Connection connection = util.getConnection()) {
 *        // Use the connection for your database operations
 *        System.out.println("Connection established successfully.");
 *    } catch (SQLException e) {
 *        e.printStackTrace(); // Handle the exception appropriately
 *    }
 *    ```
 *
 * 2. Executing an Update (INSERT, UPDATE, DELETE) Statement:
 *
 *    ```java
 *    DatabaseUtil util = new DatabaseUtil();
 *
 *    String insertSql = "INSERT INTO my_table (column_name) VALUES ('example data')";
 *
 *    try {
 *        int rowsAffected = util.executeUpdate(insertSql);
 *        System.out.println(rowsAffected + " row(s) inserted.");
 *    } catch (SQLException e) {
 *        e.printStackTrace(); // Handle the exception appropriately
 *    }
 *    ```
 *
 * 3. Executing a Query and Processing the Result Set:
 *
 *    ```java
 *    DatabaseUtil util = new DatabaseUtil();
 *
 *    String selectSql = "SELECT * FROM my_table";
 *
 *    try {
 *        ResultSet resultSet = util.executeQuery(selectSql);
 *
 *        while (resultSet.next()) {
 *            // Process each row in the result set
 *            String columnValue = resultSet.getString("column_name");
 *            System.out.println("Column Value: " + columnValue);
 *        }
 *    } catch (SQLException e) {
 *        e.printStackTrace(); // Handle the exception appropriately
 *    }
 *    ```
 *
 * 4. Inserting Data Using a Prepared Statement:
 *
 *    ```java
 *    DatabaseUtil util = new DatabaseUtil();
 *
 *    String dataToInsert = "example data";
 *
 *    try {
 *        int rowsAffected = util.insertData(dataToInsert);
 *        System.out.println(rowsAffected + " row(s) inserted.");
 *    } catch (SQLException e) {
 *        e.printStackTrace(); // Handle the exception appropriately
 *    }
 *    ```
 *
 * 5. Updating a Record Based on Record ID:
 *
 *    ```java
 *    DatabaseUtil util = new DatabaseUtil();
 *
 *    String tableName = "my_table";
 *    String idColumnName = "id";
 *    int recordId = 1;
 *
 *    Map<String, Object> columnValues = new HashMap<>();
 *    columnValues.put("column_name", "updated_value");
 *
 *    try {
 *        int rowsAffected = util.updateRecordById(tableName, idColumnName, recordId, columnValues);
 *        System.out.println(rowsAffected + " row(s) updated.");
 *    } catch (SQLException e) {
 *        e.printStackTrace(); // Handle the exception appropriately
 *    }
 *    ```
 *
 * 6. Deleting a Record Based on Record ID:
 *
 *    ```java
 *    DatabaseUtil util = new DatabaseUtil();
 *
 *    String tableName = "my_table";
 *    String idColumnName = "id";
 *    int recordId = 1;
 *
 *    try {
 *        int rowsAffected = util.deleteRecordById(tableName, idColumnName, recordId);
 *        System.out.println(rowsAffected + " row(s) deleted.");
 *    } catch (SQLException e) {
 *        e.printStackTrace(); // Handle the exception appropriately
 *    }
 *    ```
 *
 * Note: Ensure to replace placeholders like "my_table" and "column_name" with actual table and column names.
 */

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
   * Inserts a record into the specified table with the given column values,
   * excluding the record ID (assumes an auto-incremented primary key).
   *
   * @param tableName      The name of the table where the record will be inserted.
   * @param columnValues   A map where keys are column names, and values are the corresponding values
   *                       to be inserted into the respective columns.
   * @return The number of rows affected by the insertion.
   * @throws SQLException If a database access error occurs.
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
        insertSql.toString(),
        Statement.RETURN_GENERATED_KEYS
      )
    ) {
      int parameterIndex = 1;
      for (Object value : columnValues.values()) {
        statement.setObject(parameterIndex++, value);
      }

      int rowsAffected = statement.executeUpdate();

      // Retrieve the auto-generated key (assuming it's an auto-incremented PK)
      try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          // Retrieve the generated ID and use it as needed
          int generatedId = generatedKeys.getInt(1);
          System.out.println("Generated ID: " + generatedId);
        }
      }

      return rowsAffected;
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
