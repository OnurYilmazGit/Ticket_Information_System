/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GenericGenerator(name="civicanga" , strategy="increment")
    @GeneratedValue(generator="civicanga")
    @Column(name = "event_id")
    private int id;
    @Column(name = "event_name")
    private String name;
    @Column(name = "event_type")
    private String type;
    @Column (name = "location")
    private String location;
    @Column (name = "start_time")
    private String starTime;
    @Column (name = "date")
    @Type(type="date")
    private Date date;
    @Column (name = "price")
    private double price;
    @Column (name = "available_tickets")
    private int availableTickets;
    

    public Event() {
    }

    public Event(int id, String name, String type, String location, String starTime, Date date, double price, int availableTickets) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.starTime = starTime;
        this.date = date;
        this.price = price;
        this.availableTickets = availableTickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStarTime() {
        return starTime;
    }

    public void setStarTime(String starTime) {
        this.starTime = starTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }
    
    
    
    
    
}
