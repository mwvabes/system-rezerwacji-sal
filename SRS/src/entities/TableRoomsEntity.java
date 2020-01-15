package entities;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "table_rooms", schema = "srs", catalog = "")
@Immutable
public class TableRoomsEntity {
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

  @Id
  @Column(name = "ID_room", nullable = false)
  public int getIdRoom() {
    return idRoom;
  }

  public void setIdRoom(int idRoom) {
    this.idRoom = idRoom;
  }

  @Basic
  @Column(name = "roomName", nullable = false, length = 30)
  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  @Basic
  @Column(name = "roomFullName", nullable = false, length = 300)
  public String getRoomFullName() {
    return roomFullName;
  }

  public void setRoomFullName(String roomFullName) {
    this.roomFullName = roomFullName;
  }

  @Basic
  @Column(name = "seats", nullable = false)
  public int getSeats() {
    return seats;
  }

  public void setSeats(int seats) {
    this.seats = seats;
  }

  @Basic
  @Column(name = "floor", nullable = false, length = 10)
  public String getFloor() {
    return floor;
  }

  public void setFloor(String floor) {
    this.floor = floor;
  }

  @Basic
  @Column(name = "wing", nullable = false, length = 20)
  public String getWing() {
    return wing;
  }

  public void setWing(String wing) {
    this.wing = wing;
  }

  @Basic
  @Column(name = "max_time_reservation", nullable = false)
  public int getMaxTimeReservation() {
    return maxTimeReservation;
  }

  public void setMaxTimeReservation(int maxTimeReservation) {
    this.maxTimeReservation = maxTimeReservation;
  }

  @Basic
  @Column(name = "reservation_ability", nullable = false)
  public byte getReservationAbility() {
    return reservationAbility;
  }

  public void setReservationAbility(byte reservationAbility) {
    this.reservationAbility = reservationAbility;
  }

  @Basic
  @Column(name = "ID_building", nullable = false)
  public int getIdBuilding() {
    return idBuilding;
  }

  public void setIdBuilding(int idBuilding) {
    this.idBuilding = idBuilding;
  }

  @Basic
  @Column(name = "buildingName", nullable = false, length = 200)
  public String getBuildingName() {
    return buildingName;
  }

  public void setBuildingName(String buildingName) {
    this.buildingName = buildingName;
  }

  @Basic
  @Column(name = "buildingFullName", nullable = false, length = 300)
  public String getBuildingFullName() {
    return buildingFullName;
  }

  public void setBuildingFullName(String buildingFullName) {
    this.buildingFullName = buildingFullName;
  }

  @Basic
  @Column(name = "town", nullable = false, length = 50)
  public String getTown() {
    return town;
  }

  public void setTown(String town) {
    this.town = town;
  }

  @Basic
  @Column(name = "address", nullable = false, length = 50)
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Basic
  @Column(name = "area", nullable = false, length = 30)
  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  @Basic
  @Column(name = "id_type", nullable = false)
  public int getIdType() {
    return idType;
  }

  public void setIdType(int idType) {
    this.idType = idType;
  }

  @Basic
  @Column(name = "typeName", nullable = false, length = 30)
  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TableRoomsEntity that = (TableRoomsEntity) o;
    return idRoom == that.idRoom &&
        seats == that.seats &&
        maxTimeReservation == that.maxTimeReservation &&
        reservationAbility == that.reservationAbility &&
        idBuilding == that.idBuilding &&
        idType == that.idType &&
        Objects.equals(roomName, that.roomName) &&
        Objects.equals(roomFullName, that.roomFullName) &&
        Objects.equals(floor, that.floor) &&
        Objects.equals(wing, that.wing) &&
        Objects.equals(buildingName, that.buildingName) &&
        Objects.equals(buildingFullName, that.buildingFullName) &&
        Objects.equals(town, that.town) &&
        Objects.equals(address, that.address) &&
        Objects.equals(area, that.area) &&
        Objects.equals(typeName, that.typeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idRoom, roomName, roomFullName, seats, floor, wing, maxTimeReservation, reservationAbility, idBuilding, buildingName, buildingFullName, town, address, area, idType, typeName);
  }
}
