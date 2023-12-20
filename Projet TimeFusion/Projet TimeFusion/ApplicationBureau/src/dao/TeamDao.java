package dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import model.Team;

public class TeamDao extends GenericDao {

  private static final String TABLE_NAME = "Team";
  private final Map<String, Class<?>> schema = new HashMap<>();

  public TeamDao() {
    super(TABLE_NAME);
    defineSchema();
  }

  @Override
  protected void defineSchema() {
    try {
      schema.put("id", Integer.class);
      schema.put("name", String.class);
      schema.put("created_at", String.class); // Consider using a more appropriate type
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

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

  @Override
  public int insertRecord(Object obj) throws SQLException {
    if (!(obj instanceof Team)) {
      throw new IllegalArgumentException("Invalid object type.");
    }

    Team team = (Team) obj;

    if (!this.validateSchema(team)) {
      throw new IllegalArgumentException(
        "Team object does not adhere to the expected schema."
      );
    }

    Map<String, Object> columnValues = new HashMap<>();
    for (String columnName : schema.keySet()) {
      columnValues.put(columnName, getColumnValue(columnName, team));
    }

    return this.databaseUtil.insertRecord(TABLE_NAME, columnValues);
  }

  @Override
  protected boolean validateSchema(Object obj) {
    if (!(obj instanceof Team)) {
      throw new IllegalArgumentException("Invalid object type.");
    }

    Team team = (Team) obj;

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
}
