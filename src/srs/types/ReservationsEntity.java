package srs.types;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reservations", schema = "srs", catalog = "")
public class ReservationsEntity {
    private int idUser;
    private int idRoom;
    private Date meetTimeStart;
    private Date meetTimeEnd;

    @Basic
    @Column(name = "ID_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
    @Column(name = "meet_time_start")
    public Date getMeetTimeStart() {
        return meetTimeStart;
    }

    public void setMeetTimeStart(Date meetTimeStart) {
        this.meetTimeStart = meetTimeStart;
    }

    @Basic
    @Column(name = "meet_time_end")
    public Date getMeetTimeEnd() {
        return meetTimeEnd;
    }

    public void setMeetTimeEnd(Date meetTimeEnd) {
        this.meetTimeEnd = meetTimeEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationsEntity that = (ReservationsEntity) o;
        return idUser == that.idUser &&
                idRoom == that.idRoom &&
                Objects.equals(meetTimeStart, that.meetTimeStart) &&
                Objects.equals(meetTimeEnd, that.meetTimeEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idRoom, meetTimeStart, meetTimeEnd);
    }
}
