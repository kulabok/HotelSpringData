package hotel.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by kulabok on 06.05.2016.
 */
@Entity
@Table (name = "bill")
public class Bill {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private int id;
    @ManyToOne
    @JoinColumn (name = "request_id")
    private Request request;
    @OneToOne
    @JoinColumn (name = "roomnumber")
    private Room room;
    @Column (name = "cost", nullable = false)
    private int cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
