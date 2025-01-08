package hw1;

import hw2.CoworkingStorage;
import hw3.CustomClassLoader;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    ArrayList<CoworkingSpace> spaces;
    CustomerService customerService = new CustomerService();
    Scanner scanner = new Scanner(System.in);
    AdminService adminService = new AdminService(spaces);

    private static final String PLUGIN_PATH = "plugins"; // path to load class

    // Loading spaces from the file coworking_spaces at startup
    public Main() {
        spaces = CoworkingStorage.loadSpacesFromFile();
        adminService = new AdminService(spaces);
        customerService = new CustomerService();
    }

    public void run() {

        boolean exit = false;

        while (!exit) {
            String mainMenu = """
                    << Welcome to a Coworking Space Reservation System >>
                    1. Admin Login
                    2. User Login
                    3. Load Plugin
                    4. Exit
                    Enter your choice: 
                    """;

            System.out.println(mainMenu);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> adminMenu();
                case 2 -> customerMenu();
                case 3 -> loadPlugins(); // loading plugins
                case 4 -> {
                    CoworkingStorage.saveSpacesToFile(spaces); // Saving spaces before we exit the app
                    exit = true;
                    System.out.println("Exiting");
                }
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

    // loadPlugins: Method to load plugins
    private void loadPlugins() {
        try {
            CustomClassLoader loader = new CustomClassLoader(PLUGIN_PATH);
            System.out.println("Enter the class name:");
            String className = scanner.nextLine();

            Class<?> loadedClass = loader.loadClass(className);

            // Using Optional and Lambda for a Plugin Loading
            Optional.of(loadedClass) // wrapping the loaded class
                    .map(clc -> {
                        try {
                            return clc.getDeclaredConstructor().newInstance(); // if class exists, then creating an instance
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                    })
                    .ifPresent(plugin -> { // invoking a method, if it's present
                        try {
                            loadedClass.getMethod("run").invoke(plugin);
                        } catch (Exception e) {
                            System.out.println("Error running plugin: " + e.getMessage());
                        }
                    });
        } catch (Exception e) {
            System.out.println("Error loading plugin: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }
}