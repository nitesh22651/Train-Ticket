package TrainBooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection connection;

    public DBConnection() {
        this.connection = getDBConnection();
    }

    // Method to establish a database connection
    private Connection getDBConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/train_booking";
            String user = "system"; 
            String password = "user"; 

            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public Connection getConnection() {
        return connection;
    }
}
