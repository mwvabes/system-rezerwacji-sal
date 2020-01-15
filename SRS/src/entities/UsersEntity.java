package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "srs", catalog = "")
public class UsersEntity {
  private int idUser;
  private String name;
  private String surname;
  private byte reservationAbility;
  private String description;
  private int role;
  private String username;
  private String password;
  private Collection<ReservationsEntity> reservationsByIdUser;

  @Id
  @Column(name = "ID_user")
  public int getIdUser() {
    return idUser;
  }

  public void setIdUser(int idUser) {
    this.idUser = idUser;
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
  @Column(name = "surname")
  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
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
  @Column(name = "description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Basic
  @Column(name = "role")
  public int getRole() {
    return role;
  }

  public void setRole(int role) {
    this.role = role;
  }

  @Basic
  @Column(name = "username")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Basic
  @Column(name = "password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UsersEntity that = (UsersEntity) o;
    return idUser == that.idUser &&
        reservationAbility == that.reservationAbility &&
        role == that.role &&
        Objects.equals(name, that.name) &&
        Objects.equals(surname, that.surname) &&
        Objects.equals(description, that.description) &&
        Objects.equals(username, that.username) &&
        Objects.equals(password, that.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idUser, name, surname, reservationAbility, description, role, username, password);
  }

  @OneToMany(mappedBy = "usersByIdUser")
  public Collection<ReservationsEntity> getReservationsByIdUser() {
    return reservationsByIdUser;
  }

  public void setReservationsByIdUser(Collection<ReservationsEntity> reservationsByIdUser) {
    this.reservationsByIdUser = reservationsByIdUser;
  }
}
