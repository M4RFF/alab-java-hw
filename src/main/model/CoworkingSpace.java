package model;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "coworking_spaces")
public class CoworkingSpace implements Serializable {
    // Using private fields to implement encapsulation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "space_id")
    private int spaceID;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "available", nullable = false)
    private boolean available;

    public CoworkingSpace(int spaceID, String type, double price, boolean available) {
        this.spaceID = spaceID;
        this.type = type;
        this.price = price;
        this.available = available;
    }

    public int getSpaceID() {
        return spaceID;
    }
    public String getType() {
        return type;
    }
    public double getPrice() {
        return price;
    }
    public boolean isAvailable() {
        return available;
    }

    public void setSpaceID(int spaceID) {
        this.spaceID = spaceID;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Display: method for viewing space info
    public String displayInfo() {
        return "ID: " + spaceID +
                " Type: " + type +
                " Price: " + price +
                " Available: " + available;
    }
}

