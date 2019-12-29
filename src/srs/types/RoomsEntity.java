package srs.types;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rooms", schema = "srs", catalog = "")
public class RoomsEntity {
    private int idRoom;
    private int idBuilding;
    private String name;
    private int seats;
    private int maxTimeReservation;
    private byte reservationAbility;
    private int idType;
  private String fullName;
  private String floor;
  private String wing;

    public RoomsEntity() {
    }

    public RoomsEntity(int idRoom, int idBuilding, String name, int seats, int maxTimeReservation, byte reservationAbility, int idType) {
        this.idRoom = idRoom;
        this.idBuilding = idBuilding;
        this.name = name;
        this.seats = seats;
        this.maxTimeReservation = maxTimeReservation;
        this.reservationAbility = reservationAbility;
        this.idType = idType;
    }

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
    @Column(name = "seats")
    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
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
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRoom, idBuilding, name, seats, maxTimeReservation, reservationAbility, idType);
    }

    @Override
    public String toString() {
        return "RoomsEntity{" +
                "idRoom=" + idRoom +
                ", idBuilding=" + idBuilding +
                ", name='" + name + '\'' +
                ", seats=" + seats +
                ", maxTimeReservation=" + maxTimeReservation +
                ", reservationAbility=" + reservationAbility +
                ", idType=" + idType +
                '}';
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
}
