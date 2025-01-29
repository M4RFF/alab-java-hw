package service;

import DataAccessObjects.CoworkingSpaceSoC;
import model.CoworkingSpace;
import model.Reservations;
import model.CoworkingStorage;
import model.SpaceNotFoundException;
import repository.CoworkingSpaceRepo;

import java.util.*;


public class AdminService <T extends Reservations>{

    private List<CoworkingSpace> spaces;
    private HashMap<Integer, T> reservations;  // Using HashMap for managing reservations
    private Scanner scanner;  // Scanner for input
    private CoworkingSpaceRepo coworkingSpaceRepo;

    public AdminService(ArrayList<CoworkingSpace> spaces) {

        this.coworkingSpaceRepo = new CoworkingSpaceRepo();
        this.reservations = new HashMap<>(); // Initializing HashMap
        this.scanner = new Scanner(System.in);
        this.spaces = coworkingSpaceRepo.getSpaces(); // Fetching spaces from the db

        // Initializing a default if there's no spaces in db
        if (this.spaces.isEmpty()) {
            this.spaces.add(new CoworkingSpace(1, "Open Space", 1500.0, true));
            this.spaces.add(new CoworkingSpace(2, "Private Room", 2500.0, true));
            this.spaces.add(new CoworkingSpace(3, "Meeting Room", 3000.0, true));
            System.out.println("Default coworking spaces added.");

            this.spaces.forEach(coworkingSpaceRepo::saveSpace); // Saving to the db
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
        System.out.println("Space added!");
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

            coworkingSpaceRepo.deleteSpace(spaceToRemove.getSpaceID());
            spaces.remove(spaceToRemove);
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

    public void viewSpace() {
        List<CoworkingSpace> spaces = coworkingSpaceRepo.getSpaces();
        if(spaces.isEmpty()) {
            System.out.println("There is no available coworking spaces!");
        } else {
            spaces.forEach(space -> System.out.println(space.displayInfo()));
        }
    }
}

