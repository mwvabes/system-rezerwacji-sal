package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "equipment_info", schema = "srs")
public class EquipmentInfoEntity {
  private int idEquipment;
  private String name;
  private String description;
  private int idRoom;
  private int amount;
  private int idAlloc;

  @Basic
  @Column(name = "ID_equipment")
  public int getIdEquipment() {
    return idEquipment;
  }

  public void setIdEquipment(int idEquipment) {
    this.idEquipment = idEquipment;
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
  @Column(name = "description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
  @Column(name = "amount")
  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Basic
  @Id
  @Column(name = "ID_alloc")
  public int getIdAlloc() {
    return idAlloc;
  }

  public void setIdAlloc(int idAlloc) {
    this.idAlloc = idAlloc;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EquipmentInfoEntity that = (EquipmentInfoEntity) o;
    return idEquipment == that.idEquipment &&
            idRoom == that.idRoom &&
            amount == that.amount &&
            idAlloc == that.idAlloc &&
            Objects.equals(name, that.name) &&
            Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idEquipment, name, description, idRoom, amount, idAlloc);
  }
}
