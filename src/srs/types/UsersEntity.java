package srs.types;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "srs", catalog = "")
public class UsersEntity {
    private int idUser;
    private String name;
    private String surname;
    private byte reservationAbility;

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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UsersEntity that = (UsersEntity) o;
//        return idUser == that.idUser &&
//                surname == that.surname &&
//                Objects.equals(name, that.name) &&
//                Arrays.equals(reservationAbility, that.reservationAbility);
//    }

//    @Override
//    public int hashCode() {
//        int result = Objects.hash(idUser, name, surname);
//        result = 31 * result + Arrays.hashCode(reservationAbility);
//        return result;
//    }
}
