package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Example of using the DatabaseUtil class for various database operations.
 */

/**
 * Establishing a Connection:
 *
 * public static void main(String[] args) {
 *     DatabaseUtil util = new DatabaseUtil();
 *
 *     try (Connection connection = util.getConnection()) {
 *         // Use the connection for your database operations
 *         System.out.println("Connection established successfully.");
 *     } catch (SQLException e) {
 *         e.printStackTrace(); // Handle the exception appropriately
 *     }
 * }
 */

/**
 * Executing an Update (INSERT, UPDATE, DELETE) Statement:
 *
 * public static void main(String[] args) {
 *     DatabaseUtil util = new DatabaseUtil();
 *
 *     String insertSql = "INSERT INTO my_table (column_name) VALUES ('example data')";
 *
 *     try {
 *         int rowsAffected = util.executeUpdate(insertSql);
 *         System.out.println(rowsAffected + " row(s) inserted.");
 *     } catch (SQLException e) {
 *         e.printStackTrace(); // Handle the exception appropriately
 *     }
 * }
 */

/**
 * Executing a Query and Processing the Result Set:
 *
 * public static void main(String[] args) {
 *     DatabaseUtil util = new DatabaseUtil();
 *
 *     String selectSql = "SELECT * FROM my_table";
 *
 *     try {
 *         ResultSet resultSet = util.executeQuery(selectSql);
 *
 *         while (resultSet.next()) {
 *             // Process each row in the result set
 *             String columnValue = resultSet.getString("column_name");
 *             System.out.println("Column Value: " + columnValue);
 *         }
 *     } catch (SQLException e) {
 *         e.printStackTrace(); // Handle the exception appropriately
 *     }
 * }
 */

/**
 * Inserting Data Using a Prepared Statement:
 *
 * public static void main(String[] args) {
 *     DatabaseUtil util = new DatabaseUtil();
 *
 *     String dataToInsert = "example data";
 *
 *     try {
 *         int rowsAffected = util.insertData(dataToInsert);
 *         System.out.println(rowsAffected + " row(s) inserted.");
 *     } catch (SQLException e) {
 *         e.printStackTrace(); // Handle the exception appropriately
 *     }
 * }
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
   * Inserts data into a table using a prepared statement.
   *
   * @param data The data to insert.
   * @return The number of rows affected by the insertion.
   * @throws SQLException If a database access error occurs.
   */
  public int insertData(String data) throws SQLException {
    String insertSql = "INSERT INTO my_table (column_name) VALUES (?)";
    try (
      Connection connection = getConnection();
      PreparedStatement statement = connection.prepareStatement(insertSql)
    ) {
      statement.setString(1, data);
      return statement.executeUpdate();
    }
  }

  /**
   * Executes an update (INSERT, UPDATE, DELETE) using a prepared statement.
   *
   * @param statement The prepared statement to execute.
   * @return The number of rows affected by the update.
   * @throws SQLException If a database access error occurs.
   */
  public int executeUpdate(PreparedStatement statement) throws SQLException {
    return statement.executeUpdate();
  }

  /**
   * Executes a query using a prepared statement and returns the result set.
   *
   * @param statement The prepared statement to execute.
   * @return The result set containing the query results.
   * @throws SQLException If a database access error occurs.
   */
  public ResultSet executeQuery(PreparedStatement statement)
    throws SQLException {
    return statement.executeQuery();
  }
}
