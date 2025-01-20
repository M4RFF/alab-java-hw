package src.main.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// This class is created for managing connections
public class DBmanager {

    private static final String URL = "jdbc:postgresql://localhost:5432/coworking_space";
    private static final String USER = "postgres";
    private static final String PASSWORD = "max123";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
