package hw1;

import hw2.SpaceNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerService <T extends Reservations> {

    private ArrayList<CoworkingSpace> spaces;  // List of coworking spaces
    private  ArrayList<T> reservations;  // List of reservations
    private Scanner scanner;  // Scanner for input
    private int reservationIdCounter = 1; // generate a unique reservation ID for each reservation


    // Customer constructor initializes lists and scanner
    public CustomerService() {

        this.spaces = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.scanner = new Scanner(System.in);

    }



    public void findSpace() {

        boolean found = false;

        for (CoworkingSpace space : spaces) {
            if (space.isAvailable()) {
                System.out.println("ID: " + space.getSpaceID() +
                        " Type: " + space.getType() +
                        " Price: " + space.getPrice()
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("There are no available spaces!");
        }
    }

    public void makeReservation() {

        System.out.println("Enter the space ID to reserve it: ");
        int spaceID = scanner.nextInt();
        scanner.nextLine();

        CoworkingSpace spaceToReserve = null;
        for (CoworkingSpace space : spaces) {
            if (space.getSpaceID() == spaceID && space.isAvailable()) {
                spaceToReserve = space;
                break;
            }
        }

        // Throwing an exception if the space not found
        try {
            if (spaceToReserve == null) {
                throw new SpaceNotFoundException("Error: Space with ID" + spaceID + "not available");
            }
            System.out.println("Enter your name: ");
            String customerName = scanner.nextLine();

            System.out.println("Enter the current date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            System.out.println("Enter the start time (HH:MM): ");
            String startTime = scanner.nextLine();

            System.out.println("Enter the end time (HH:MM): ");
            String endTime = scanner.nextLine();

            reservations.add((T) new Reservations(reservationIdCounter++, spaceID, customerName, date, startTime, endTime));
            spaceToReserve.setAvailable(false);

            System.out.println("Reservation Made Successfully!");
        } catch (SpaceNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void viewMyReservations() {

        if (reservations.isEmpty()) {
            System.out.println("You have no reservations yet!");
        } else {
            for (Reservations res : reservations) {
                System.out.println(res.displayInfo());
            }
        }
    }

    public void cancelReservation() {

        System.out.println("Enter the reservation ID to cancel it: ");
        int reservationID = scanner.nextInt();

        Reservations reservationToCancel = null;
        for (Reservations reservation : reservations) {
            if (reservation.getReservationID() == reservationID) {
                reservationToCancel = reservation;
                break;
            }
        }

        if (reservationToCancel != null) {
            for (CoworkingSpace space : spaces) {
                if (space.getSpaceID() == reservationToCancel.getCoworkingSpaceID()) {
                    space.setAvailable(true);
                    break;
                }
            }
            System.out.println("Reservation Cancelled Successfully!");
        } else {
            System.out.println("There is no reservation with that ID!");
        }
    }

}
