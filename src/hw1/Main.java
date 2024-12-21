package hw1;

import java.util.Scanner;

public class Main {

    AdminService adminService = new AdminService();
    CustomerService customerService = new CustomerService();
    Scanner scanner = new Scanner(System.in);

    public void run() {

        boolean exit = false;

        while (!exit) {
            String mainMenu = """
                    << Welcome to a Coworking Space Reservation System >>
                    1. Admin Login
                    2. User Login
                    3. Exit
                    Enter your choice: 
                    """;

            System.out.println(mainMenu);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> adminMenu();
                case 2 -> customerMenu();
                case 3 -> exit = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    public void adminMenu() {

        boolean backToMain = false;

        while (!backToMain) {
            String adminMenu = """
                    << Admin Menu >>
                    1. Add a new coworking space
                    2. Remove a coworking space
                    3. View all reservations
                    4. Back to Main Menu
                    Enter your choice:
                    """;

            System.out.println(adminMenu);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> adminService.addSpace();
                case 2 -> adminService.removeSpace();
                case 3 -> adminService.viewReservations();
                case 4 -> backToMain = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    public void customerMenu() {

        boolean backToMain = false;

        while (!backToMain) {
            String customerMenu = """
                    << Customer Menu >>
                    1. Find available spaces
                    2. Make a reservation
                    3. View my reservations
                    4. Cancel a reservation
                    5. Back to Main Menu
                    Enter your choice:
                    """;

            System.out.println(customerMenu);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> customerService.findSpace();
                case 2 -> customerService.makeReservation();
                case 3 -> customerService.viewMyReservations();
                case 4 -> customerService.cancelReservation();
                case 5 -> backToMain = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }


    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }
}
