package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reservations", schema = "srs", catalog = "")
public class ReservationsEntity {
  private int idReservation;
  private int idUser;
  private int idRoom;
  private Timestamp meetTimeStart;
  private Timestamp meetTimeEnd;
  private UsersEntity usersByIdUser;
  private RoomsEntity roomsByIdRoom;

  @Id
  @Column(name = "ID_reservation")
  public int getIdReservation() {
    return idReservation;
  }

  public void setIdReservation(int idReservation) {
    this.idReservation = idReservation;
  }

  @Basic
  @Column(name = "ID_user")
  public int getIdUser() {
    return idUser;
  }

  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }

  @Basic
  @Column(name = "ID_room")
  public int getIdRoom() {
    return idRoom;
  }

  public void setIdRoom(int idRoom) {
    this.idRoom = idRoom;
  }

  @Basic
  @Column(name = "meet_time_start")
  public Timestamp getMeetTimeStart() {
    return meetTimeStart;
  }

  public void setMeetTimeStart(Timestamp meetTimeStart) {
    this.meetTimeStart = meetTimeStart;
  }

  @Basic
  @Column(name = "meet_time_end")
  public Timestamp getMeetTimeEnd() {
    return meetTimeEnd;
  }

  public void setMeetTimeEnd(Timestamp meetTimeEnd) {
    this.meetTimeEnd = meetTimeEnd;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ReservationsEntity that = (ReservationsEntity) o;
    return idReservation == that.idReservation &&
        idUser == that.idUser &&
        idRoom == that.idRoom &&
        Objects.equals(meetTimeStart, that.meetTimeStart) &&
        Objects.equals(meetTimeEnd, that.meetTimeEnd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idReservation, idUser, idRoom, meetTimeStart, meetTimeEnd);
  }

  @ManyToOne
  @JoinColumn(name = "ID_user", referencedColumnName = "ID_user", nullable = false)
  public UsersEntity getUsersByIdUser() {
    return usersByIdUser;
  }

  public void setUsersByIdUser(UsersEntity usersByIdUser) {
    this.usersByIdUser = usersByIdUser;
  }

  @ManyToOne
  @JoinColumn(name = "ID_room", referencedColumnName = "ID_room", nullable = false)
  public RoomsEntity getRoomsByIdRoom() {
    return roomsByIdRoom;
  }

  public void setRoomsByIdRoom(RoomsEntity roomsByIdRoom) {
    this.roomsByIdRoom = roomsByIdRoom;
  }
}
