package com.timefusion.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Team {

  private Long id;
  private String name;
  private String description;
  private User creator;
  private LocalDateTime createdAt;

  /**
   * Represents a team in the application.
   * @param id the id of the team
   * @param name the name of the team
   * @param createdAt the date and time of the creation of the team
   * @param creator the creator of the team
   * @param description the description of the team
   */
  public Team(
    Long id,
    String name,
    LocalDateTime createdAt,
    User creator,
    String description
  ) {
    this.id = id;
    this.name = name;
    this.createdAt = createdAt;
    this.creator = creator;
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
    this(null, name, createdAt, creator, description);
  }

  /**
   * Returns the ID of the team.
   * @return the ID of the team
   */
  public Long getId() {
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
   * Returns the date and time of the creation of the team.
   * @return the date and time of the creation of the team
   */
  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  /**
   * Returns the creator of the team.
   * @return the creator of the team
   */
  public User getCreator() {
    return this.creator;
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
  public void setId(Long id) {
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
   * Sets the date and time of the creation of the team.
   *
   * @param createdAt the date and time to set
   */
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Sets the creator of the team.
   *
   * @param creator the creator to set
   */
  public void setCreator(User creator) {
    this.creator = creator;
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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd HH:mm:ss"
    );
    String formattedDate = createdAt != null
      ? formatter.format(createdAt)
      : "null";

    return String.format(
      "Team{id=%d, name='%s', createdAt='%s', creator=%s, description='%s'}",
      id,
      name,
      formattedDate,
      creator,
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
      this.id.equals(team.id) &&
      this.name.equals(team.name) &&
      this.createdAt.equals(team.createdAt) &&
      this.creator.equals(team.creator) &&
      this.description.equals(team.description)
    );
  }

  public static void main(String[] args) {
    User user = new User(1, "email", "firstName", "lastName", "password");
    Team team = new Team(1L, "name", LocalDateTime.now(), user, "description");
    System.out.println(team);
  }
}
