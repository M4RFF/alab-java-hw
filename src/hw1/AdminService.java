package hw1;

import hw2.CoworkingStorage;
import hw2.SpaceNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;


public class AdminService {

    private  ArrayList<CoworkingSpace> spaces;  // List of coworking spaces
    private  ArrayList<Reservations> reservations;  // List of reservations
    private  Scanner scanner;  // Scanner for input


    public AdminService(ArrayList<CoworkingSpace> spaces) {

        this.spaces = (spaces != null) ? spaces : new ArrayList<>();
        this.reservations = new ArrayList<>();
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

        // Checking for duplicate space ID
        for (CoworkingSpace space : spaces) {
            if (space.getSpaceID() == ID) {
                System.out.println("Space with that ID" + ID + "already exists!");
                return;
            }
        }

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

        CoworkingStorage.saveSpacesToFile(spaces);
    }

    public void removeSpace() {

        String removePrompt = """
                Enter the space ID to remove:
                """;
        System.out.println(removePrompt);
        int ID = scanner.nextInt();

        CoworkingSpace spaceToRemove = null;

        for (CoworkingSpace space : spaces) {
            if (space.getSpaceID() == ID) {
                spaceToRemove = space;
                break;
            }
        }

        // Throwing an exception if the space not found
        try {
            if(spaceToRemove == null) {
                throw new SpaceNotFoundException("Space with ID: " + ID + " doesn't exist!");
            }
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
            for (Reservations reservation : reservations) {
                System.out.println(reservation.displayInfo());
            }
        }
    }

}
