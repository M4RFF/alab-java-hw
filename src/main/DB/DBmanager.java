package src.main.DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


// This class is created for managing connections
public class DBmanager {

    private static final String PROP_FILE = "src/main/DB/db.properties";

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    static {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(PROP_FILE));

            URL = prop.getProperty("db.url");
            USERNAME = prop.getProperty("db.username");
            PASSWORD = prop.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't load db properties!", e);
        }
    }


    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

}
