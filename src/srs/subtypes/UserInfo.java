package srs.subtypes;

public class UserInfo {

  int ID_user;
  String name;
  String surname;
  int reservation_ability;
  String description;
  int role;
  String username;

  public UserInfo() {
  }

  public UserInfo(int ID_user, String name, String surname, int reservation_ability, String description, int role, String username) {
    this.ID_user = ID_user;
    this.name = name;
    this.surname = surname;
    this.reservation_ability = reservation_ability;
    this.description = description;
    this.role = role;
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getID_user() {
    return ID_user;
  }

  public void setID_user(int ID_user) {
    this.ID_user = ID_user;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public int getReservation_ability() {
    return reservation_ability;
  }

  public void setReservation_ability(int reservation_ability) {
    this.reservation_ability = reservation_ability;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getRole() {
    return role;
  }

  public void setRole(int role) {
    this.role = role;
  }
}
