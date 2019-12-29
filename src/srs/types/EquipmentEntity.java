package srs.types;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "equipment", schema = "srs", catalog = "")
public class EquipmentEntity {
    private int idEquipment;
    private String name;
    private String description;

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentEntity that = (EquipmentEntity) o;
        return idEquipment == that.idEquipment &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEquipment, name, description);
    }
}
