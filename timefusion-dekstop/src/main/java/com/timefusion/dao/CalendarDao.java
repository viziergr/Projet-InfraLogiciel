package com.timefusion.dao;

import com.timefusion.model.Calendar;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * The CalendarDao class is responsible for performing database operations related to the Calendar entity.
 * It extends the GenericDao class and provides specific implementations for inserting, updating, and deleting Calendar records.
 */
public class CalendarDao extends GenericDao<Calendar> {

  private static final String TABLE_NAME = "Calendar";
  private final Map<String, Class<?>> schema = new HashMap<>();

  public CalendarDao() {
    super(TABLE_NAME);
    defineSchema();
  }

  /**
   * Defines the schema for the CalendarDao class.
   * This method is responsible for defining the structure of the database table for calendars.
   * It sets the column names and their corresponding data types.
   */
  @Override
  protected void defineSchema() {
    try {
      schema.put("id", Long.class);
      schema.put("name", String.class);
      schema.put("user_id", Long.class);
      schema.put("team_id", Long.class);
      schema.put("event_id", Long.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Retrieves the value of a specific column from the given Calendar object.
   *
   * @param columnName the name of the column to retrieve the value from
   * @param calendar the Calendar object from which to retrieve the value
   * @return the value of the specified column in the Calendar object, or null if an exception occurs
   */
  private Object getColumnValue(String columnName, Calendar calendar) {
    try {
      return Calendar.class.getDeclaredMethod(
          "get" +
          columnName.substring(0, 1).toUpperCase() +
          columnName.substring(1)
        )
        .invoke(calendar);
    } catch (Exception e) {
      e.printStackTrace(); // Handle the exception appropriately
      return null;
    }
  }

  /**
   * Inserts a record into the database.
   *
   * @param calendar the calendar object to be inserted
   * @return the number of rows affected by the insert operation
   * @throws SQLException             if an error occurs while inserting the record
   * @throws IllegalArgumentException if the object does not adhere to the expected schema
   */
  @Override
  public int insertRecord(Calendar calendar) throws SQLException {
    if (!this.validateSchema(calendar)) {
      throw new IllegalArgumentException(
        "Calendar object does not adhere to the expected schema."
      );
    }

    Map<String, Object> columnValues = new HashMap<>();
    for (String columnName : schema.keySet()) {
      columnValues.put(columnName, getColumnValue(columnName, calendar));
    }

    return super.databaseUtil.insertRecord(TABLE_NAME, columnValues);
  }

  /**
   * Validates the schema of a calendar object.
   *
   * @param calendar The calendar object to validate.
   * @return true if the schema is valid, false otherwise.
   */
  @Override
  protected boolean validateSchema(Calendar calendar) {
    for (Map.Entry<String, Class<?>> entry : schema.entrySet()) {
      String columnName = entry.getKey();
      Class<?> expectedType = entry.getValue();
      Object columnValue = getColumnValue(columnName, calendar);

      if (columnValue == null || !expectedType.isInstance(columnValue)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Updates a record in the database by its ID.
   *
   * @param updatedCalendar the object representing the record to be updated
   * @return the number of rows affected by the update operation
   * @throws SQLException             if an error occurs while updating the record
   * @throws IllegalArgumentException if the object does not adhere to the expected schema
   */
  @Override
  protected int updateRecordById(Calendar updatedCalendar) throws SQLException {
    if (!this.validateSchema(updatedCalendar)) {
      throw new IllegalArgumentException(
        "Updated Calendar object does not adhere to the expected schema."
      );
    }

    Map<String, Object> columnValues = new HashMap<>();
    for (String columnName : schema.keySet()) {
      columnValues.put(columnName, getColumnValue(columnName, updatedCalendar));
    }

    long recordId = updatedCalendar.getId();

    return super.databaseUtil.updateRecordById(
      TABLE_NAME,
      "id",
      recordId,
      columnValues
    );
  }

  /**
   * Deletes a record from the database by its ID.
   *
   * @param calendarToDelete the object representing the record to be deleted
   * @return the number of rows affected by the delete operation
   * @throws SQLException if an error occurs while deleting the record
   */
  @Override
  protected int deleteRecordById(Calendar calendarToDelete)
    throws SQLException {
    long recordId = calendarToDelete.getId();

    return super.databaseUtil.deleteRecordById(TABLE_NAME, "id", recordId);
  }
}
