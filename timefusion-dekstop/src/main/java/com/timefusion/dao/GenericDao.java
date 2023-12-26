package com.timefusion.dao;

import com.timefusion.util.DatabaseUtil;
import java.sql.SQLException;

/**
 * The GenericDao class is an abstract class that provides a generic implementation for data access objects (DAOs).
 * It defines common methods for interacting with a database table, such as inserting, updating, and deleting records.
 * Subclasses of GenericDao should provide specific implementations for defining the schema, validating the schema,
 * inserting records, updating records, and deleting records.
 *
 * @param <T> The type of the entity for which the DAO is created.
 */
public abstract class GenericDao<T> {

  protected final DatabaseUtil databaseUtil;
  protected final String tableName;

  /**
   * Constructs a new GenericDao object with the specified table name.
   *
   * @param tableName the name of the database table
   */
  public GenericDao(String tableName) {
    this.databaseUtil = new DatabaseUtil();
    this.tableName = tableName;
  }

  /**
   * Defines the expected schema for a record.
   * Subclasses should implement this method to define the schema for the specific table.
   */
  protected abstract void defineSchema();

  /**
   * Validates the schema of a record.
   * Subclasses should implement this method to validate the schema of the specific table.
   *
   * @param entity the entity representing the record to be validated
   * @return true if the schema is valid, false otherwise
   * @throws SQLException if an error occurs while validating the schema
   */
  protected abstract boolean validateSchema(T entity) throws SQLException;

  /**
   * Inserts a record into the table.
   * Subclasses should implement this method to insert a record into the specific table.
   *
   * @param entity the entity representing the record to be inserted
   * @return the number of rows affected by the insert operation
   * @throws SQLException if an error occurs while inserting the record
   */
  protected abstract int insertRecord(T entity) throws SQLException;

  /**
   * Updates a record in the table.
   * Subclasses should implement this method to update a record in the specific table.
   *
   * @param entity the entity representing the record to be updated
   * @return the number of rows affected by the update operation
   * @throws SQLException if an error occurs while updating the record
   */
  protected abstract int updateRecordById(T entity) throws SQLException;

  /**
   * Deletes a record from the table.
   * Subclasses should implement this method to delete a record from the specific table.
   *
   * @param entity the entity representing the record to be deleted
   * @return the number of rows affected by the delete operation
   * @throws SQLException if an error occurs while deleting the record
   */
  protected abstract int deleteRecordById(T entity) throws SQLException;
}
