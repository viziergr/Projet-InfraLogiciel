package com.timefusion.model;

import java.util.Objects;

public class Eventparticipant {

  private int id;
  private int participantId;
  private int eventId;
  private String participant_type;

  /**
   * Constructor for Eventparticipant
   * @param id
   * @param participantId
   * @param eventId
   * @param participant_type
   */
  public Eventparticipant(
    int id,
    int participantId,
    int eventId,
    String participant_type //team or user
  ) {
    this.id = id;
    this.participantId = participantId;
    this.eventId = eventId;
    this.participant_type = participant_type;
  }

  /**
   * Constructor for Eventparticipant
   * @param participantId
   * @param eventId
   * @param participant_type
   */
  public Eventparticipant(
    int participantId,
    int eventId,
    String participant_type
  ) {
    this(0, participantId, eventId, participant_type);
  }

  /**
   * Returns the ID of the event participant.
   * @return the ID of the event participant
   */
  public int getId() {
    return this.id;
  }

  /**
   * Returns the ID of the user.
   * @return the ID of the user
   */
  public int getparticipantId() {
    return this.participantId;
  }

  /**
   * Returns the ID of the event.
   * @return the ID of the event
   */
  public int getEventId() {
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
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Sets the ID of the user.
   * @param participantId the ID of the user
   */
  public void setparticipantId(int participantId) {
    this.participantId = participantId;
  }

  /**
   * Sets the ID of the event.
   * @param eventId the ID of the event
   */
  public void setEventId(int eventId) {
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
   * Returns true if the given object is also an Eventparticipant and has the same values for id, participantId, eventId, and participant_type.
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
      Objects.equals(participantId, eventparticipant.participantId) &&
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
      ", participantId=" +
      participantId +
      ", eventId=" +
      eventId +
      ", participant_type=" +
      participant_type +
      '}'
    );
  }
}
