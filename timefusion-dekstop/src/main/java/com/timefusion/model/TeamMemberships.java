package com.timefusion.model;

import java.util.Objects;

/**
 * Represents a team membership in the application.
 */
public class TeamMemberships {

  private Long id;
  private Long userId;
  private Long teamId;
  private String role;

  /**
   * Constructor for TeamMemberships
   * @param id
   * @param userId
   * @param teamId
   * @param role
   */
  public TeamMemberships(Long id, Long userId, Long teamId, String role) {
    this.id = id;
    this.userId = userId;
    this.teamId = teamId;
    this.role = role;
  }

  public TeamMemberships(Long userId, Long teamId, String role) {
    this(null, userId, teamId, role);
  }

  /**
   * Returns the ID of the team membership.
   * @return the ID of the team membership
   */
  public Long getId() {
    return this.id;
  }

  /**
   * Returns the ID of the user.
   * @return the ID of the user
   */
  public Long getUserId() {
    return this.userId;
  }

  /**
   * Returns the ID of the team.
   * @return the ID of the team
   */
  public Long getTeamId() {
    return this.teamId;
  }

  /**
   * Returns the role of the user in the team.
   * @return the role of the user in the team
   */
  public String getRole() {
    return this.role;
  }

  /**
   * Sets the role of the user in the team.
   * @param role the role of the user in the team
   */
  public void setRole(String role) {
    this.role = role;
  }

  /**
   * Sets the ID of the team membership.
   * @param id the ID of the team membership
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Sets the ID of the user.
   * @param userId the ID of the user
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * Sets the ID of the team.
   * @param teamId the ID of the team
   */
  public void setTeamId(Long teamId) {
    this.teamId = teamId;
  }

  /**
   * Returns a string representation of the team membership.
   * @return a string representation of the team membership
   */
  @Override
  public String toString() {
    return (
      "TeamMemberships {id=" +
      id +
      ", userId=" +
      userId +
      ", teamId=" +
      teamId +
      ", role=" +
      role +
      "}"
    );
  }

  /**
   * Returns true or false depending on whether the given object is equal to this object.
   * @param o the object to compare
   * @return true if the given object is equal to this object
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof TeamMemberships)) {
      return false;
    }
    TeamMemberships teamMemberships = (TeamMemberships) o;
    return (
      Objects.equals(id, teamMemberships.id) &&
      Objects.equals(userId, teamMemberships.userId) &&
      Objects.equals(teamId, teamMemberships.teamId) &&
      Objects.equals(role, teamMemberships.role)
    );
  }
}