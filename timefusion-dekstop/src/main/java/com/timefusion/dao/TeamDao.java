package com.timefusion.dao;

import com.timefusion.model.Team;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * The TeamDao class is responsible for performing database operations related to the Team entity.
 * It extends the GenericDao class and provides specific implementations for inserting, updating, and deleting Team records.
 */
public class TeamDao extends GenericDao<Team> {

  private static final String TABLE_NAME = "Team";
  private final Map<String, Class<?>> schema = new HashMap<>();

  public TeamDao() {
    super(TABLE_NAME);
    defineSchema();
  }

  /**
   * Defines the schema for the TeamDao class.
   * This method is responsible for defining the structure of the database table for teams.
   * It sets the column names and their corresponding data types.
   */
  @Override
  protected void defineSchema() {
    try {
      schema.put("id", Integer.class);
      schema.put("name", String.class);
      schema.put("created_at", java.sql.Timestamp.class); // Consider using a more appropriate type
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Retrieves the value of a specific column from the given Team object.
   *
   * @param columnName the name of the column to retrieve the value from
   * @param team the Team object from which to retrieve the value
   * @return the value of the specified column in the Team object, or null if an exception occurs
   */
  private Object getColumnValue(String columnName, Team team) {
    try {
      return Team.class.getDeclaredMethod(
          "get" +
          columnName.substring(0, 1).toUpperCase() +
          columnName.substring(1)
        )
        .invoke(team);
    } catch (Exception e) {
      e.printStackTrace(); // Handle the exception appropriately
      return null;
    }
  }

  /**
   * Inserts a record into the database.
   *
   * @param obj The object representing the record to be inserted.
   * @return The number of rows affected by the insert operation.
   * @throws SQLException If an error occurs while inserting the record.
   * @throws IllegalArgumentException If the provided object is not of type Team or does not adhere to the expected schema.
   */
  @Override
  public int insertRecord(Team team) throws SQLException {
    if (!this.validateSchema(team)) {
      throw new IllegalArgumentException(
        "Team object does not adhere to the expected schema."
      );
    }

    Map<String, Object> columnValues = new HashMap<>();
    for (String columnName : schema.keySet()) {
      columnValues.put(columnName, getColumnValue(columnName, team));
    }

    return super.databaseUtil.insertRecord(TABLE_NAME, columnValues);
  }

  /**
   * Validates the schema of a record.
   *
   * @param team the Team object representing the record to be validated
   * @return true if the schema is valid, false otherwise
   */
  @Override
  protected boolean validateSchema(Team team) {
    for (Map.Entry<String, Class<?>> entry : schema.entrySet()) {
      String columnName = entry.getKey();
      Class<?> expectedType = entry.getValue();
      Object columnValue = getColumnValue(columnName, team);

      if (columnValue == null || !expectedType.isInstance(columnValue)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Updates a record in the database.
   *
   * @param obj the object representing the record to be updated
   * @return the number of rows affected by the update operation
   * @throws SQLException if an error occurs while updating the record
   * @throws IllegalArgumentException if the provided object is not of type Team or does not adhere to the expected schema
   */
  @Override
  protected int updateRecordById(Team updatedTeam) throws SQLException {
    if (!this.validateSchema(updatedTeam)) {
      throw new IllegalArgumentException(
        "Updated Team object does not adhere to the expected schema."
      );
    }

    Map<String, Object> columnValues = new HashMap<>();
    for (String columnName : schema.keySet()) {
      columnValues.put(columnName, getColumnValue(columnName, updatedTeam));
    }

    long recordId = updatedTeam.getId();

    return super.databaseUtil.updateRecordById(
      TABLE_NAME,
      "id",
      recordId,
      columnValues
    );
  }

  /**
   * Deletes a record from the database.
   *
   * @param obj the object representing the record to be deleted
   * @return the number of rows affected by the delete operation
   * @throws SQLException if an error occurs while deleting the record
   * @throws IllegalArgumentException if the provided object is not of type Team or does not adhere to the expected schema
   */
  @Override
  protected int deleteRecordById(Team teamToDelete) throws SQLException {
    long recordId = teamToDelete.getId();

    return super.databaseUtil.deleteRecordById(TABLE_NAME, "id", recordId);
  }
}
