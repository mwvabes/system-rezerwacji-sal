package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "room_types", schema = "srs")
public class RoomTypesEntity {
  private int idType;
  private String name;
  private Collection<RoomsEntity> roomsByIdType;

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

  @OneToMany(mappedBy = "roomTypesByIdType")
  @JoinColumn(name="id_type",referencedColumnName="id_type")
  public Collection<RoomsEntity> getRoomsByIdType() {
    return roomsByIdType;
  }

  public void setRoomsByIdType(Collection<RoomsEntity> roomsByIdType) {
    this.roomsByIdType = roomsByIdType;
  }

  @Override
  public String toString() {
    return name;
  }

  public RoomTypesEntity() {
  }

  public RoomTypesEntity(String name) {
    this.name = name;
  }

}
