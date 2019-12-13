package srs.types;

public class Room {

  private int ID_room;
  private int ID_building;
  private String name;
  private int seats;
  private int max_time_reservation;
  private boolean reservation_ability;
  private int ID_type;

  public Room() {
  }

  public Room(int ID_room, int ID_building, String name, int seats, int max_time_reservation, boolean reservation_ability, int ID_type) {
    this.ID_room = ID_room;
    this.ID_building = ID_building;
    this.name = name;
    this.seats = seats;
    this.max_time_reservation = max_time_reservation;
    this.reservation_ability = reservation_ability;
    this.ID_type = ID_type;
  }

  public int getID_room() {
    return ID_room;
  }

  public void setID_room(int ID_room) {
    this.ID_room = ID_room;
  }

  public int getID_building() {
    return ID_building;
  }

  public void setID_building(int ID_building) {
    this.ID_building = ID_building;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSeats() {
    return seats;
  }

  public void setSeats(int seats) {
    this.seats = seats;
  }

  public int getMax_time_reservation() {
    return max_time_reservation;
  }

  public void setMax_time_reservation(int max_time_reservation) {
    this.max_time_reservation = max_time_reservation;
  }

  public boolean isReservation_ability() {
    return reservation_ability;
  }

  public void setReservation_ability(boolean reservation_ability) {
    this.reservation_ability = reservation_ability;
  }

  public int getID_type() {
    return ID_type;
  }

  public void setID_type(int ID_type) {
    this.ID_type = ID_type;
  }
}
