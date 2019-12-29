package srs.types;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "room_types", schema = "srs", catalog = "")
public class RoomTypesEntity {
    private int idType;
    private String name;

    @Id
    @Column(name = "id_type")
    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomTypesEntity that = (RoomTypesEntity) o;
        return idType == that.idType &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idType, name);
    }
}
