package entities;

import java.sql.Timestamp;

public class ReservationHistoryEntity {

  String fullUsername;
  String dateStart;
  String dateEnd;

  public ReservationHistoryEntity() {
  }

  public ReservationHistoryEntity(String name, String surname, Timestamp dateStart, Timestamp dateEnd) {
    this.fullUsername = name + " " + surname;
    this.dateStart = translateTimestamp(dateStart);
    this.dateEnd = translateTimestamp(dateEnd);
  }

  public String getFullUsername() {
    return fullUsername;
  }

  public void setFullUsername(String fullUsername) {
    this.fullUsername = fullUsername;
  }

  public String getDateStart() {
    return dateStart;
  }

  public void setDateStart(String dateStart) {
    this.dateStart = dateStart;
  }

  public String getDateEnd() {
    return dateEnd;
  }

  public void setDateEnd(String dateEnd) {
    this.dateEnd = dateEnd;
  }

  public String translateTimestamp(Timestamp ts) {
    String date = String.valueOf(ts.toLocalDateTime().minusHours(1)).replaceAll("T", " ");
    String newDate = date.substring(8,10) + "-" + date.substring(5,7) + "-" + date.substring(0,4) + " " + date.substring(11,16);
    return newDate;
  }
}
