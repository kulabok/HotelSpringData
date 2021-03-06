package hotel.entity;

import hotel.entity.enums.RoomClass;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Calendar;
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
    @Temporal(TemporalType.DATE)
    @Column (name = "start", nullable = false)
    private Date start;
    @Temporal(TemporalType.DATE)
    @Column (name = "end", nullable = false)
    private Date end;
    @Column (name = "processed", nullable = false)
    private boolean isProcessed;

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

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean isProcessed) {
        this.isProcessed = isProcessed;
    }
}
