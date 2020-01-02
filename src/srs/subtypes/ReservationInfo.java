package srs.subtypes;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ReservationInfo {

  private String userName;
  private int ID_user;
  private int ID_room;
  private Timestamp timeStart;
  private Timestamp timeEnd;
  private LocalDateTime timeStartDate;
  private LocalDateTime timeEndDate;

  public ReservationInfo() {
  }

  public ReservationInfo(String name, String surname) {
    this.userName = name + " " + surname;
  }

  public ReservationInfo(Timestamp timeStart, Timestamp timeEnd) {
    this.timeStart = timeStart;
    this.timeEnd = timeEnd;
  }

  public ReservationInfo(int ID_user, int ID_room, Timestamp timeStart, Timestamp timeEnd) {
    this.ID_user = ID_user;
    this.ID_room = ID_room;
    this.timeStart = timeStart;
    this.timeEnd = timeEnd;
  }

  public ReservationInfo(String name, String surname, Timestamp timeStart, Timestamp timeEnd) {
    this.userName = name + " " + surname;
    this.timeStartDate = timeStart.toLocalDateTime();
    this.timeEndDate = timeEnd.toLocalDateTime();
  }

  public boolean isTimeSlotValid() {
    if (timeStart.before(timeEnd)) return true;
    else return false;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public LocalDateTime getTimeStartDate() {
    return timeStartDate;
  }

  public void setTimeStartDate(LocalDateTime timeStartDate) {
    this.timeStartDate = timeStartDate;
  }

  public LocalDateTime getTimeEndDate() {
    return timeEndDate;
  }

  public void setTimeEndDate(LocalDateTime timeEndDate) {
    this.timeEndDate = timeEndDate;
  }

  public int getID_user() {
    return ID_user;
  }

  public void setID_user(int ID_user) {
    this.ID_user = ID_user;
  }

  public int getID_room() {
    return ID_room;
  }

  public void setID_room(int ID_room) {
    this.ID_room = ID_room;
  }

  public Timestamp getTimeStart() {
    return timeStart;
  }

  public void setTimeStart(Timestamp timeStart) {
    this.timeStart = timeStart;
  }

  public Timestamp getTimeEnd() {
    return timeEnd;
  }

  public void setTimeEnd(Timestamp timeEnd) {
    this.timeEnd = timeEnd;
  }

  @Override
  public String toString() {
    return "ReservationInfo{" +
        "name='" + userName + '\'' +
        ", ID_user=" + ID_user +
        ", ID_room=" + ID_room +
        ", timeStart=" + timeStart +
        ", timeEnd=" + timeEnd +
        ", timeStartDate=" + timeStartDate +
        ", timeEndDate=" + timeEndDate +
        '}';
  }
}
