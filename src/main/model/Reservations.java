package model;

import jakarta.persistence.*;

// Reservations: represents a reservation
@Entity
@Table(name = "reservations")
public class Reservations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int reservationID;

    @Column(name = "coworking_space_id", nullable = false)
    private int coworkingSpaceID;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "start_time", nullable = false)
    private String startTime;

    @Column(name = "end_time", nullable = false)
    private String endTime;

    @ManyToOne
    @JoinColumn(name = "space_id", nullable = false)
    private CoworkingSpace space;

    public Reservations(int reservationID, int coworkingSpaceID, String customerName, String date, String startTime, String endTime) {
        this.reservationID = reservationID;
        this.coworkingSpaceID = coworkingSpaceID;
        this.customerName = customerName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Constructor for JPA which has no arguments inside
    public Reservations() {
    }

    // Getters
    public int getReservationID() {
        return reservationID;
    }
    public int getCoworkingSpaceID() {
        return coworkingSpaceID;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getDate() {
        return date;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {
        return endTime;
    }

    public String displayInfo() {
        return "Reservation ID: " + reservationID +
                " Space ID: " + coworkingSpaceID +
                " Name: " + customerName +
                " Date: " + date +
                " Time: " + startTime + "-" + endTime;
    }
}
