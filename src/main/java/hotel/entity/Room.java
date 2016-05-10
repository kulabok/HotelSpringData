package hotel.entity;

import hotel.entity.enums.RoomClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by kulabok on 06.05.2016.
 */
@Entity
@Table (name = "room")
public class Room {
    @Id
    @Column(name = "number", length = 3, nullable = false)
    private int roomNumber;
    @Column (name = "roomclass", nullable = false)
    private RoomClass roomClass;
    @Column (name = "personsmax", nullable = false)
    private int personsMax;
    @Column (name = "costperperson", nullable = false)
    private int costPerPerson;
    @Column (name = "available", nullable = false)
    private int available;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public int getPersonsMax() {
        return personsMax;
    }

    public void setPersonsMax(int personsMax) {
        this.personsMax = personsMax;
    }

    public int getCostPerPerson() {
        return costPerPerson;
    }

    public void setCostPerPerson(int costPerPerson) {
        this.costPerPerson = costPerPerson;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
