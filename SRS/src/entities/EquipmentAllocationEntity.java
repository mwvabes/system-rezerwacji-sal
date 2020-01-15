package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "equipment_allocation", schema = "srs", catalog = "")
public class EquipmentAllocationEntity {
  private int idAlloc;
  private int idRoom;
  private int idEquipment;
  private int amount;
  private RoomsEntity roomsByIdRoom;
  private EquipmentEntity equipmentByIdEquipment;

  @Id
  @Column(name = "ID_alloc")
  public int getIdAlloc() {
    return idAlloc;
  }

  public void setIdAlloc(int idAlloc) {
    this.idAlloc = idAlloc;
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
  @Column(name = "ID_equipment")
  public int getIdEquipment() {
    return idEquipment;
  }

  public void setIdEquipment(int idEquipment) {
    this.idEquipment = idEquipment;
  }

  @Basic
  @Column(name = "amount")
  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EquipmentAllocationEntity that = (EquipmentAllocationEntity) o;
    return idAlloc == that.idAlloc &&
        idRoom == that.idRoom &&
        idEquipment == that.idEquipment &&
        amount == that.amount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(idAlloc, idRoom, idEquipment, amount);
  }

  @ManyToOne
  @JoinColumn(name = "ID_room", referencedColumnName = "ID_room", nullable = false)
  public RoomsEntity getRoomsByIdRoom() {
    return roomsByIdRoom;
  }

  public void setRoomsByIdRoom(RoomsEntity roomsByIdRoom) {
    this.roomsByIdRoom = roomsByIdRoom;
  }

  @ManyToOne
  @JoinColumn(name = "ID_equipment", referencedColumnName = "ID_equipment", nullable = false)
  public EquipmentEntity getEquipmentByIdEquipment() {
    return equipmentByIdEquipment;
  }

  public void setEquipmentByIdEquipment(EquipmentEntity equipmentByIdEquipment) {
    this.equipmentByIdEquipment = equipmentByIdEquipment;
  }
}
