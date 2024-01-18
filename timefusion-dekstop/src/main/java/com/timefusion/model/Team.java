package com.timefusion.model;

import java.time.LocalDateTime;

public class Team {

  private int id;
  private String name;
  private String description;

  /**
   * Represents a team in the application.
   * @param id the id of the team
   * @param name the name of the team
   * @param createdAt the date and time of the creation of the team
   * @param creator the creator of the team
   * @param description the description of the team
   */
  public Team(int id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  /**
   * Represents a team in the application.
   * @param name the name of the team
   * @param createdAt the date and time of the creation of the team
   * @param creator the creator of the team
   * @param description the description of the team
   */
  public Team(
    String name,
    LocalDateTime createdAt,
    User creator,
    String description
  ) {
    this(0, name, description);
  }

  /**
   * Returns the ID of the team.
   * @return the ID of the team
   */
  public int getId() {
    return this.id;
  }

  /**
   * Returns the name of the team.
   * @return the name of the team
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the description of the team.
   * @return the description of the team
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Sets the ID of the team.
   *
   * @param id the ID to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Sets the name of the team.
   *
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the description of the team.
   *
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Returns a string representation of the Team object.
   *
   * @return The string representation of the Team object.
   */
  @Override
  public String toString() {
    return String.format(
      "Team{id=%d, name='%s', description='%s'}",
      id,
      name,
      description
    );
  }

  /**
   * Returns a hash code value for the Team object.
   *
   * @return A hash code value for the Team object.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Team)) return false;
    Team team = (Team) o;
    return (
      this.id == team.id &&
      this.name.equals(team.name) &&
      this.description.equals(team.description)
    );
  }

  public static void main(String[] args) {
    Team team = new Team(1, "name", "description");
    System.out.println(team);
  }
}
