package TrainBooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainTicketBooking {
    // Method to book a ticket
    public static synchronized void bookTicket(String userName, String trainName, DBConnection dbConnection) {
        Connection connection = dbConnection.getConnection();
        try {
            String query = "SELECT total_seats, booked_seats FROM tickets WHERE train_name = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, trainName);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int totalSeats = rs.getInt("total_seats");
                int bookedSeats = rs.getInt("booked_seats");

                if (bookedSeats < totalSeats) {
                    // Book a seat
                    String updateQuery = "UPDATE tickets SET booked_seats = booked_seats + 1 WHERE train_name = ?";
                    PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
                    updateStmt.setString(1, trainName);

                    int rowsUpdated = updateStmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println(userName + " successfully booked a ticket on " + trainName);
                    }
                } else {
                    System.out.println(userName + " failed to book a ticket. No seats available on " + trainName);
                }
            } else {
                System.out.println("Train not found: " + trainName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
