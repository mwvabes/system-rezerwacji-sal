package srs.types;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "equipment_allocation", schema = "srs", catalog = "")
public class EquipmentAllocationEntity {
    private int idRoom;
    private int idEquipment;
    private int amount;

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
        return idRoom == that.idRoom &&
                idEquipment == that.idEquipment &&
                amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRoom, idEquipment, amount);
    }
}
