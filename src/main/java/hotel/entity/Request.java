package hotel.entity;

import hotel.entity.enums.RoomClass;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by kulabok on 06.05.2016.
 */
@Entity
@Table (name = "request")
public class Request {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private int id;
    @ManyToOne
    @JoinColumn (name = "client_id")
    private User user;
    @Column (name = "roomclass", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomClass roomClass;
    @Column (name = "personsquantity", nullable = false)
    private int personQuantity;
    @Column (name = "start", nullable = false)
    private java.sql.Date start;
    @Column (name = "end", nullable = false)
    private java.sql.Date end;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public int getPersonQuantity() {
        return personQuantity;
    }

    public void setPersonQuantity(int personQuantity) {
        this.personQuantity = personQuantity;
    }

    public java.sql.Date getStart() {
        return start;
    }

    public void setStart(java.sql.Date start) {
        this.start = start;
    }

    public java.sql.Date getEnd() {
        return end;
    }

    public void setEnd(java.sql.Date end) {
        this.end = end;
    }
}
