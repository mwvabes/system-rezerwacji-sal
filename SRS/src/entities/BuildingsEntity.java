package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "buildings", schema = "srs")
public class BuildingsEntity {
  private int idBuilding;
  private String name;
  private String fullName;
  private String town;
  private String address;
  private String area;
  private Collection<RoomsEntity> roomsByIdBuilding;

  @Id
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BuildingsEntity that = (BuildingsEntity) o;
    return idBuilding == that.idBuilding &&
        Objects.equals(name, that.name) &&
        Objects.equals(fullName, that.fullName) &&
        Objects.equals(town, that.town) &&
        Objects.equals(address, that.address) &&
        Objects.equals(area, that.area);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idBuilding, name, fullName, town, address, area);
  }

  @OneToMany(mappedBy = "buildingsByIdBuilding")
  public Collection<RoomsEntity> getRoomsByIdBuilding() {
    return roomsByIdBuilding;
  }

  public void setRoomsByIdBuilding(Collection<RoomsEntity> roomsByIdBuilding) {
    this.roomsByIdBuilding = roomsByIdBuilding;
  }

  public BuildingsEntity() {
  }

  public BuildingsEntity(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    String ret = name;
    if (area != null) ret+= " | " + area;
    return ret;
  }
}
