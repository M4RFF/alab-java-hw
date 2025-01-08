package hw1;

// Reservations: represents a reservation
public class Reservations {

    private int reservationID;
    private int coworkingSpaceID;
    private String customerName;
    private String date;
    private String startTime;
    private String endTime;

    public Reservations(int reservationID, int coworkingSpaceID, String customerName, String date, String startTime, String endTime) {
        this.reservationID = reservationID;
        this.coworkingSpaceID = coworkingSpaceID;
        this.customerName = customerName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
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