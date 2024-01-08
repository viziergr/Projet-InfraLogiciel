package com.timefusion.model;

import java.util.Objects;

public class Eventparticipant {

  private Long id;
  private Long userId;
  private Long eventId;
  private String participant_type;

  /**
   * Constructor for Eventparticipant
   * @param id
   * @param userId
   * @param eventId
   * @param participant_type
   */
  public Eventparticipant(
    Long id,
    Long userId,
    Long eventId,
    String participant_type
  ) {
    this.id = id;
    this.userId = userId;
    this.eventId = eventId;
    this.participant_type = participant_type;
  }

  /**
   * Constructor for Eventparticipant
   * @param userId
   * @param eventId
   * @param participant_type
   */
  public Eventparticipant(Long userId, Long eventId, String participant_type) {
    this(null, userId, eventId, participant_type);
  }

  /**
   * Returns the ID of the event participant.
   * @return the ID of the event participant
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
   * Returns the ID of the event.
   * @return the ID of the event
   */
  public Long getEventId() {
    return this.eventId;
  }

  /**
   * Returns the type of the participant.
   * @return the type of the participant
   */
  public String getParticipant_type() {
    return this.participant_type;
  }

  /**
   * Sets the ID of the event participant.
   * @param id the ID of the event participant
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
   * Sets the ID of the event.
   * @param eventId the ID of the event
   */
  public void setEventId(Long eventId) {
    this.eventId = eventId;
  }

  /**
   * Sets the type of the participant.
   * @param participant_type the type of the participant
   */
  public void setParticipant_type(String participant_type) {
    this.participant_type = participant_type;
  }

  /**
   * Compares this Eventparticipant object with the specified object for equality.
   * Returns true if the given object is also an Eventparticipant and has the same values for id, userId, eventId, and participant_type.
   *
   * @param o the object to compare with
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Eventparticipant)) {
      return false;
    }
    Eventparticipant eventparticipant = (Eventparticipant) o;
    return (
      Objects.equals(id, eventparticipant.id) &&
      Objects.equals(userId, eventparticipant.userId) &&
      Objects.equals(eventId, eventparticipant.eventId) &&
      Objects.equals(participant_type, eventparticipant.participant_type)
    );
  }

  /**
   * Returns a string representation of the Eventparticipant.
   * @return a string representation of the Eventparticipant
   */
  @Override
  public String toString() {
    return (
      "Eventparticipant{" +
      "id=" +
      id +
      ", userId=" +
      userId +
      ", eventId=" +
      eventId +
      ", participant_type=" +
      participant_type +
      '}'
    );
  }
}
