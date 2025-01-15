package src.hw1;

import src.hw2.CoworkingStorage;
import src.hw2.SpaceNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;


public class AdminService <T extends Reservations>{

    private  ArrayList<CoworkingSpace> spaces;
    private HashMap<Integer, T> reservations;  // Using HashMap for managing reservations
    private  Scanner scanner;  // Scanner for input


    public AdminService(ArrayList<CoworkingSpace> spaces) {

        this.spaces = (spaces != null) ? spaces : new ArrayList<>();
        this.reservations = new HashMap<>(); // Initializing HashMap
        this.scanner = new Scanner(System.in);


        if (this.spaces.isEmpty()) {
            this.spaces.add(new CoworkingSpace(1, "Open Space", 1500.0, true));
            this.spaces.add(new CoworkingSpace(2, "Private Room", 2500.0, true));
            this.spaces.add(new CoworkingSpace(3, "Meeting Room", 3000.0, true));
            System.out.println("Default coworking spaces added.");
            CoworkingStorage.saveSpacesToFile(this.spaces);
        }
    }


    public void addSpace() {

        String addPrompt = """
                Enter the space ID:
                """;
        System.out.println(addPrompt);
        int ID = scanner.nextInt();
        scanner.nextLine();

        // Checking for duplicate space ID (Using Optional with Stream API)
        Optional<CoworkingSpace> existSpace = spaces.stream()
                .filter(space -> space.getSpaceID() == ID)
                .findFirst();

        existSpace.ifPresent(space -> {
            System.out.println("Space with this ID already exists!");
        });

        String typePrompt = """
                Would you like Open Space or Private Room or Meeting Room?: 
                """;
        System.out.println(typePrompt);
        String type = scanner.nextLine();

        String pricePrompt = """
                Enter the price:
                """;
        System.out.println(pricePrompt);
        double price = scanner.nextDouble();
        scanner.nextLine();


        CoworkingSpace newSpace = new CoworkingSpace(ID, type, price, true);
        spaces.add(newSpace);
        CoworkingStorage.saveSpacesToFile(spaces);
    }

    public void removeSpace() {

        String removePrompt = """
                Enter the space ID to remove:
                """;
        System.out.println(removePrompt);
        int ID = scanner.nextInt();
        scanner.nextLine();

        // Using Lambda for simplifying conditions
        CoworkingSpace spaceToRemove = spaces.stream()
                .filter(space -> space.getSpaceID() == ID)
                .findFirst()
                .orElse(null);

        // Throwing an exception if the space not found
        try {
            if(spaceToRemove == null) {
                throw new SpaceNotFoundException("Space with ID: " + ID + " doesn't exist!");
            }
            spaces.remove(spaceToRemove);
            CoworkingStorage.saveSpacesToFile(spaces);
            System.out.println("Coworking Space Removed Successfully!");
        } catch (SpaceNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewReservations() {

        if (reservations.isEmpty()) {
            System.out.println("There are no reservations yet!");
        } else {
            CoworkingStorage.printMap(reservations);
        }
    }
}

