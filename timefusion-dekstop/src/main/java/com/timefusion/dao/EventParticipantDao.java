package com.timefusion.dao;

import com.timefusion.model.Eventparticipant;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventParticipantDao extends GenericDao<Eventparticipant> {

  private static final String TABLE_NAME = "event_participant";
  private final Map<String, Class<?>> schema = new HashMap<>();

  public EventParticipantDao() throws SQLException {
    super(TABLE_NAME);
    defineSchema();
  }

  private Object getColumnValue(
    String columnName,
    Eventparticipant eventparticipant
  ) {
    switch (columnName) {
      case "id":
        return eventparticipant.getId();
      case "participant_id":
        return eventparticipant.getparticipantId();
      case "event_id":
        return eventparticipant.getEventId();
      case "participant_type":
        return eventparticipant.getParticipant_type();
      default:
        return null;
    }
  }

  private Eventparticipant mapResultSetToEventparticipant(
    Map<String, Object> resultSet
  ) {
    if (resultSet.isEmpty()) {
      return null;
    } else if (resultSet.size() == 1) {
      throw new IllegalArgumentException("Invalid number of results found");
    }
    return new Eventparticipant(
      (Integer) resultSet.get("id"),
      (Integer) resultSet.get("participant_id"),
      (Integer) resultSet.get("event_id"),
      (String) resultSet.get("participant_type")
    );
  }

  private Map<String, Object> mapEventparticipantToColumnValues(
    Eventparticipant eventParticipant
  ) {
    Map<String, Object> columnValues = new HashMap<>();
    for (String columnName : schema.keySet()) {
      columnValues.put(
        columnName,
        getColumnValue(columnName, eventParticipant)
      );
    }
    return columnValues;
  }

  public int insertEventParticipantRecord(Eventparticipant eventParticipant)
    throws SQLException {
    return super.databaseUtil.insertRecord(
      tableName,
      this.mapEventparticipantToColumnValues(eventParticipant)
    );
  }

  public int updateEventParticipantRecord(Eventparticipant eventParticipant)
    throws SQLException {
    Map<String, Object> columnValues =
      this.mapEventparticipantToColumnValues(eventParticipant);
    return super.databaseUtil.updateRecordById(
      tableName,
      "id",
      eventParticipant.getId(),
      columnValues
    );
  }

  public int deleteTEventParticipantRecord(Eventparticipant eventParticipant)
    throws SQLException {
    return super.databaseUtil.deleteRecordById(
      tableName,
      "id",
      eventParticipant.getId()
    );
  }

  public List<Eventparticipant> retrieveEventParticipantRecords(
    Map<String, Object> criteriaMap
  ) throws SQLException {
    return this.mapEventparticipantSetToEventparticipants(
        super.databaseUtil.retrieveRecords(tableName, criteriaMap)
      );
  }

  private List<Eventparticipant> mapEventparticipantSetToEventparticipants(
    List<Map<String, Object>> resultSet
  ) {
    List<Eventparticipant> eventparticipants = new ArrayList<>();
    for (Map<String, Object> result : resultSet) {
      Eventparticipant eventparticipant = new Eventparticipant(
        (Integer) result.get("id"),
        (Integer) result.get("participant_id"),
        (Integer) result.get("event_id"),
        (String) result.get("participant_type")
      );
      eventparticipants.add(eventparticipant);
    }
    return eventparticipants;
  }

  @Override
  protected void defineSchema() {
    try {
      schema.put("id", Integer.class);
      schema.put("participant_id", Integer.class);
      schema.put("event_id", Integer.class);
      schema.put("participant_type", String.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected int insertRecord(Eventparticipant eventParticipant)
    throws SQLException {
    return this.insertEventParticipantRecord(eventParticipant);
  }

  @Override
  protected int updateRecordByEntity(Eventparticipant eventParticipant)
    throws SQLException {
    return this.updateEventParticipantRecord(eventParticipant);
  }

  @Override
  protected List<Eventparticipant> retrieveRecords(
    String tableName,
    Map<String, Object> criteriaMap
  ) throws SQLException {
    return this.retrieveEventParticipantRecords(criteriaMap);
  }
}
