package srs.subtypes;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class ReservationInfo {

  int ID_user;
  int ID_room;
  Timestamp timeStart;
  Timestamp timeEnd;

  public ReservationInfo() {
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

  public boolean isTimeSlotValid() {
    if (timeStart.before(timeEnd)) return true;
    else return false;
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
}
