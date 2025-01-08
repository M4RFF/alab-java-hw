package hw1;

import java.io.Serializable;


// CoworkingSpace: represents a coworking space
public class CoworkingSpace implements Serializable {
    // Using private fields to implement encapsulation
    private int spaceID;
    private String type;
    private double price;
    private boolean available;

    public CoworkingSpace(int spaceID, String type, double price, boolean available) {
        this.spaceID = spaceID;
        this.type = type;
        this.price = price;
        this.available = available;
    }

    // Getters
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

    // Setters
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