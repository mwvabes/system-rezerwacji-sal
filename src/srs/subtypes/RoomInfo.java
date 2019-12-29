package srs.subtypes;

public class RoomInfo {

    int idRoom;
    String name;
    int seats;
    String type;

    public RoomInfo() {
    }

    public RoomInfo(int idRoom, String name, int seats, String type) {
        this.idRoom = idRoom;
        this.name = name;
        this.seats = seats;
        this.type = type;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
