package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "rooms", schema = "srs")
public class RoomsEntity {
  private int idRoom;
  private int idBuilding;
  private String name;
  private String fullName;
  private int seats;
  private String floor;
  private String wing;
  private int maxTimeReservation;
  private byte reservationAbility;
  private int idType;
  private Collection<EquipmentAllocationEntity> equipmentAllocationsByIdRoom;
  private Collection<ReservationsEntity> reservationsByIdRoom;
  private BuildingsEntity buildingsByIdBuilding;
  private RoomTypesEntity roomTypesByIdType;

  @Id
  @Column(name = "ID_room")
  public int getIdRoom() {
    return idRoom;
  }

  public void setIdRoom(int idRoom) {
    this.idRoom = idRoom;
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
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "fullName")
  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
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
  @Column(name = "id_type")
  public int getIdType() {
    return idType;
  }

  public void setIdType(int idType) {
    this.idType = idType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RoomsEntity that = (RoomsEntity) o;
    return idRoom == that.idRoom &&
        idBuilding == that.idBuilding &&
        seats == that.seats &&
        maxTimeReservation == that.maxTimeReservation &&
        reservationAbility == that.reservationAbility &&
        idType == that.idType &&
        Objects.equals(name, that.name) &&
        Objects.equals(fullName, that.fullName) &&
        Objects.equals(floor, that.floor) &&
        Objects.equals(wing, that.wing);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idRoom, idBuilding, name, fullName, seats, floor, wing, maxTimeReservation, reservationAbility, idType);
  }

  @OneToMany(mappedBy = "roomsByIdRoom")
  public Collection<EquipmentAllocationEntity> getEquipmentAllocationsByIdRoom() {
    return equipmentAllocationsByIdRoom;
  }

  public void setEquipmentAllocationsByIdRoom(Collection<EquipmentAllocationEntity> equipmentAllocationsByIdRoom) {
    this.equipmentAllocationsByIdRoom = equipmentAllocationsByIdRoom;
  }

  @OneToMany(mappedBy = "roomsByIdRoom")
  public Collection<ReservationsEntity> getReservationsByIdRoom() {
    return reservationsByIdRoom;
  }

  public void setReservationsByIdRoom(Collection<ReservationsEntity> reservationsByIdRoom) {
    this.reservationsByIdRoom = reservationsByIdRoom;
  }

  @ManyToOne
  @JoinColumn(name = "ID_building", referencedColumnName = "ID_building", nullable = false)
  public BuildingsEntity getBuildingsByIdBuilding() {
    return buildingsByIdBuilding;
  }

  public void setBuildingsByIdBuilding(BuildingsEntity buildingsByIdBuilding) {
    this.buildingsByIdBuilding = buildingsByIdBuilding;
  }

  @ManyToOne
  @JoinColumn(name = "id_type", referencedColumnName = "id_type", nullable = false)
  public RoomTypesEntity getRoomTypesByIdType() {
    return roomTypesByIdType;
  }

  public void setRoomTypesByIdType(RoomTypesEntity roomTypesByIdType) {
    this.roomTypesByIdType = roomTypesByIdType;
  }


  @Override
  public String toString() {
    return name;
  }
}
