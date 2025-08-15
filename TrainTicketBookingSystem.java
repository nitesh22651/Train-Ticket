package TrainBooking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrainTicketBookingSystem {
    private static final int MAX_THREADS = 5;

    public static void main(String[] args) {
        DBConnection connection = new DBConnection();

        if (connection.getConnection() == null) {
            System.out.println("Failed to connect to the database. Exiting...");
            return;
        }

        System.out.println("Welcome to the Train Ticket Booking System!");

        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

        for (int i = 1; i <= MAX_THREADS; i++) {
            int userId = i;
            executor.execute(() -> TrainTicketBooking.bookTicket("User" + userId, "Express Train", connection));
        }

        executor.shutdown();
    }
}
