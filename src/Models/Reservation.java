package Models;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
/**
 *
 * @author ecemy
 */
    @Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GenericGenerator(name="civicanga" , strategy="increment")
    @GeneratedValue(generator="civicanga")
    @Column(name="reservation_id")
    private int reservation_id;
    @Column(name = "username")
    private String username;
    @Column(name = "event_id")
    private int event_id;
     
     

    public Reservation() {
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public Reservation(String username, int event_id) {
        this.username = username;
        this.event_id = event_id;
    }


    
}
