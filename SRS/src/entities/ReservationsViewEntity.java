package entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reservations_view", schema = "srs", catalog = "")
public class ReservationsViewEntity {
  private int idRoom;
  private String roomName;
  private String roomFullName;
  private int seats;
  private String floor;
  private String wing;
  private int maxTimeReservation;
  private byte reservationAbility;
  private int idBuilding;
  private String buildingName;
  private String buildingFullName;
  private String town;
  private String address;
  private String area;
  private int idType;
  private String typeName;
  private int idReservation;
  private Timestamp meetTimeStart;
  private Timestamp meetTimeEnd;
  private int idUser;
  private String userName;
  private String userSurname;
  private String userDescription;

  @Basic
  @Column(name = "ID_room")
  public int getIdRoom() {
    return idRoom;
  }

  public void setIdRoom(int idRoom) {
    this.idRoom = idRoom;
  }

  @Basic
  @Column(name = "roomName")
  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  @Basic
  @Column(name = "roomFullName")
  public String getRoomFullName() {
    return roomFullName;
  }

  public void setRoomFullName(String roomFullName) {
    this.roomFullName = roomFullName;
  }

  @Basic
  @Column(name = "seats")
  public int getSeats() {
    return seats;
  }

  public void setSeats(int seats) {
    this.seats = seats;
  }

  @Basic
  @Column(name = "floor")
  public String getFloor() {
    return floor;
  }

  public void setFloor(String floor) {
    this.floor = floor;
  }

  @Basic
  @Column(name = "wing")
  public String getWing() {
    return wing;
  }

  public void setWing(String wing) {
    this.wing = wing;
  }

  @Basic
  @Column(name = "max_time_reservation")
  public int getMaxTimeReservation() {
    return maxTimeReservation;
  }

  public void setMaxTimeReservation(int maxTimeReservation) {
    this.maxTimeReservation = maxTimeReservation;
  }

  @Basic
  @Column(name = "reservation_ability")
  public byte getReservationAbility() {
    return reservationAbility;
  }

  public void setReservationAbility(byte reservationAbility) {
    this.reservationAbility = reservationAbility;
  }

  @Basic
  @Column(name = "ID_building")
  public int getIdBuilding() {
    return idBuilding;
  }

  public void setIdBuilding(int idBuilding) {
    this.idBuilding = idBuilding;
  }

  @Basic
  @Column(name = "buildingName")
  public String getBuildingName() {
    return buildingName;
  }

  public void setBuildingName(String buildingName) {
    this.buildingName = buildingName;
  }

  @Basic
  @Column(name = "buildingFullName")
  public String getBuildingFullName() {
    return buildingFullName;
  }

  public void setBuildingFullName(String buildingFullName) {
    this.buildingFullName = buildingFullName;
  }

  @Basic
  @Column(name = "town")
  public String getTown() {
    return town;
  }

  public void setTown(String town) {
    this.town = town;
  }

  @Basic
  @Column(name = "address")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Basic
  @Column(name = "area")
  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  @Basic
  @Column(name = "id_type")
  public int getIdType() {
    return idType;
  }

  public void setIdType(int idType) {
    this.idType = idType;
  }

  @Basic
  @Column(name = "typeName")
  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  @Basic
  @Column(name = "ID_reservation")
  public int getIdReservation() {
    return idReservation;
  }

  public void setIdReservation(int idReservation) {
    this.idReservation = idReservation;
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
    ReservationsViewEntity that = (ReservationsViewEntity) o;
    return idRoom == that.idRoom &&
            seats == that.seats &&
            maxTimeReservation == that.maxTimeReservation &&
            reservationAbility == that.reservationAbility &&
            idBuilding == that.idBuilding &&
            idType == that.idType &&
            idReservation == that.idReservation &&
            Objects.equals(roomName, that.roomName) &&
            Objects.equals(roomFullName, that.roomFullName) &&
            Objects.equals(floor, that.floor) &&
            Objects.equals(wing, that.wing) &&
            Objects.equals(buildingName, that.buildingName) &&
            Objects.equals(buildingFullName, that.buildingFullName) &&
            Objects.equals(town, that.town) &&
            Objects.equals(address, that.address) &&
            Objects.equals(area, that.area) &&
            Objects.equals(typeName, that.typeName) &&
            Objects.equals(meetTimeStart, that.meetTimeStart) &&
            Objects.equals(meetTimeEnd, that.meetTimeEnd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idRoom, roomName, roomFullName, seats, floor, wing, maxTimeReservation, reservationAbility, idBuilding, buildingName, buildingFullName, town, address, area, idType, typeName, idReservation, meetTimeStart, meetTimeEnd);
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
  @Column(name = "userName")
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Basic
  @Column(name = "userSurname")
  public String getUserSurname() {
    return userSurname;
  }

  public void setUserSurname(String userSurname) {
    this.userSurname = userSurname;
  }

  @Basic
  @Column(name = "userDescription")
  public String getUserDescription() {
    return userDescription;
  }

  public void setUserDescription(String userDescription) {
    this.userDescription = userDescription;
  }
}
