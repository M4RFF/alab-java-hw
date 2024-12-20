package hw1;

import java.util.ArrayList;
import java.util.Scanner;

// CoworkingSpace: represents a coworking space
class CoworkingSpace {
    int spaceID;
    String type;
    double price;
    boolean available;

    public CoworkingSpace(int spaceID, String type, double price, boolean available) {
        this.spaceID = spaceID;
        this.type = type;
        this.price = price;
        this.available = available;
    }
}

// Reservations: represents a reservation
class Reservations {
    int reservationID;
    int coworkingSpaceID;
    String customerName;
    String date;
    String startTime;
    String endTime;

    public Reservations(int reservationID, int coworkingSpaceID, String customerName, String date, String startTime, String endTime) {
        this.reservationID = reservationID;
        this.coworkingSpaceID = coworkingSpaceID;
        this.customerName = customerName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<CoworkingSpace> spaces = new ArrayList<>(); // list of coworking spaces
    static ArrayList<Reservations> reservations = new ArrayList<>(); // list of reservations
    static int reservationIdCounter = 1; // generate a unique reservation ID for each reservation

    public static void main(String[] args) {

        spaces.add(new CoworkingSpace(1, "Open Space", 1500.0, true));
        spaces.add(new CoworkingSpace(2, "Private Room", 2500.0, true));
        spaces.add(new CoworkingSpace(3, "Meeting Room", 3000.0, true));

        boolean exit = false;

        while (!exit) {
            System.out.println("<< Welcome to a Coworking Space Reservation System >>");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    customerMenu();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Thanks for using our service!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }



/*
* Admin Menu: This method is intended to add a new coworking space,
* remove a coworking space, or view all reservations.
* Additionally, we can return to the main menu without exiting.
*/
    private static void adminMenu() {

        boolean backToMain = false;

        while (!backToMain) {
            System.out.println("<< Admin Menu >>");
            System.out.println("1. Add a new coworking space");
            System.out.println("2. Remove a coworking space");
            System.out.println("3. View all reservations");
            System.out.println("4. Back to Main Menu");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addSpace();
                    break;
                case 2:
                    removeSpace();
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    backToMain = true;
                    System.out.println("You're back to the main menu!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void addSpace() {

        System.out.println("Enter the space ID: ");
        int ID = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Would you like Open Space or Private Space?: ");
        String type = scanner.nextLine();

        System.out.println("Enter the price: ");
        double price = scanner.nextDouble();

        spaces.add(new CoworkingSpace(ID, type, price, true));
        System.out.println("Coworking Space Added Successfully!");
    }

    private static void removeSpace() {
        System.out.println("Enter the space ID to remove: ");
        int ID = scanner.nextInt();

        CoworkingSpace spaceToRemove = null;

        for (CoworkingSpace space : spaces) {
            if (space.spaceID == ID) {
                spaceToRemove = space;
                break;
            }
        }

        if (spaceToRemove != null) {
            spaces.remove(spaceToRemove);
            System.out.println("Coworking Space Removed Successfully!");
        } else {
            System.out.println("Coworking Space with that ID does not exist!");
        }
    }

    private static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("There are no reservations yet!");
        } else {
            for (Reservations reservation : reservations) {
                System.out.println(
                        "Reservation ID: " + reservation.reservationID +
                                " Space ID: " + reservation.coworkingSpaceID +
                                " Name: " + reservation.customerName +
                                " Date: " + reservation.date +
                                " Time: " + reservation.startTime + "-" + reservation.endTime
                );
            }
        }
    }



/*
* Customer Menu: This method allows customers to
* find available spaces, make reservations, view their reservations, and cancel reservations.
* Additionally, they can return to the main menu.
*/
    private static void customerMenu() {

        System.out.println("What is your name?: ");
        scanner.nextLine();
        String customerName = scanner.nextLine();

        boolean backToMain = false;

        while (!backToMain) {
            System.out.println("<< Customer Menu >>");
            System.out.println("1. Find an available space");
            System.out.println("2. Make a reservation");
            System.out.println("3. View my reservations");
            System.out.println("4. Cancel my reservation");
            System.out.println("5. Back to Main Menu");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    findSpace();
                    break;
                case 2:
                    makeReservation(customerName);
                    break;
                case 3:
                    viewMyReservations(customerName);
                    break;
                case 4:
                    cancelReservation();
                    break;
                case 5:
                    backToMain = true;
                    System.out.println("You're back to the main menu!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    private static void findSpace() {

        boolean found = false;

        for (CoworkingSpace space : spaces) {
            if (space.available) {
                System.out.println("ID: " + space.spaceID +
                                   " Type: " + space.type +
                                   " Price: " + space.price
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("There are no available spaces!");
        }
    }

    private static void makeReservation(String customerName) {

        System.out.println("Enter the space ID to reserve it: ");
        int spaceID = scanner.nextInt();
        scanner.nextLine();

        CoworkingSpace spaceToReserve = null;
        for (CoworkingSpace space : spaces) {
            if (space.spaceID == spaceID && space.available) {
                spaceToReserve = space;
                break;
            }
        }

        if (spaceToReserve != null) {
            System.out.println("Enter the current date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            System.out.println("Enter the start time (HH:MM): ");
            String startTime = scanner.nextLine();

            System.out.println("Enter the end time (HH:MM): ");
            String endTime = scanner.nextLine();

            reservations.add(new Reservations(reservationIdCounter++, spaceID, customerName, date, startTime, endTime));
            spaceToReserve.available = false; // if the space is unavailable
            System.out.println("Reservation Made Successfully!");
        } else {
            System.out.println("There is no space with that ID or it is unavailable!");
        }
    }

    private static void viewMyReservations(String customerName) {

        boolean found = false;

        for (Reservations reservation : reservations) {
            if (reservation.customerName.equals(customerName)) {
                System.out.println(
                        "Reservation ID: " + reservation.reservationID +
                                " Space ID: " + reservation.coworkingSpaceID +
                                " Date: " + reservation.date +
                                " Time: " + reservation.startTime + "-" + reservation.endTime
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("You have no reservations yet!");
        }
    }

    private static void cancelReservation() {

        System.out.println("Enter the reservation ID to cancel it: ");
        int reservationID = scanner.nextInt();

        Reservations reservationToCancel = null;
        for (Reservations reservation : reservations) {
            if (reservation.reservationID == reservationID) {
                reservationToCancel = reservation;
                break;
            }
        }

        if (reservationToCancel != null) {
            for (CoworkingSpace space : spaces) {
                if (space.spaceID == reservationToCancel.coworkingSpaceID) {
                    space.available = true;
                    break;
                }
            }

            reservations.remove(reservationToCancel);
            System.out.println("Reservation Cancelled Successfully!");
        } else {
            System.out.println("There is no reservation with that ID!");
        }
    }
}
