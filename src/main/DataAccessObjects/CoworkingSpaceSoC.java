package src.main.DataAccessObjects;

import src.main.DB.DBmanager;
import src.main.model.CoworkingSpace;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
    According to Separation of Concerns principle. I have decided to separate different
    responsibilities into different classes
 */
public class CoworkingSpaceSoC {
    public List<CoworkingSpace> getSpaces() {
        List<CoworkingSpace> spaces = new ArrayList<>();
        String sql = "SELECT * FROM spaces";

        try (Connection conn = DBmanager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CoworkingSpace space = new CoworkingSpace(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getBoolean("is_available")
                );
                spaces.add(space);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spaces;
    }

    public void addSpace(CoworkingSpace space) {
        String sql = "INSERT INTO coworking_space (type, price, is_available) VALUES (?, ?, ?)";

        try (Connection connection = DBmanager.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, space.getType());
            pstmt.setDouble(2, space.getPrice());
            pstmt.setBoolean(3, space.isAvailable());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
