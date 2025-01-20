package src.main.DataAccessObjects;


import src.main.DB.DBmanager;
import src.main.model.Reservations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
    According to Separation of Concerns principle. I have decided to separate different
    responsibilities into different classes

    Reservation is model class that represents the data structure for reservations. And it contains properties
    and also methods get/set
    While this reservationSoC responsible for handling tha db operations like removing/adding and etc
 */
public class ReservationSoC {
    public List<Reservations> getReservations() {
        List<Reservations> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";

        try (Connection conn = DBmanager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reservations reservation = new Reservations(
                        rs.getInt("id"),
                        rs.getInt("space_id"),
                        rs.getString("customer_name"),
                        rs.getString("date"),
                        rs.getString("start_time"),
                        rs.getString("end_time")
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public void addReservation(Reservations reservation) {
        String sql = "INSERT INTO reservations (customer_name, date, start_time, end_time, space_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBmanager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reservation.getCustomerName());
            pstmt.setString(2, reservation.getDate());
            pstmt.setString(3, reservation.getStartTime());
            pstmt.setString(4, reservation.getEndTime());
            pstmt.setInt(5, reservation.getCoworkingSpaceID());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
