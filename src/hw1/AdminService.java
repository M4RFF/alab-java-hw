package hw1;

import java.util.ArrayList;
import java.util.Scanner;


public class AdminService {

    private  ArrayList<CoworkingSpace> spaces;  // List of coworking spaces
    private  ArrayList<Reservations> reservations;  // List of reservations
    private  Scanner scanner;  // Scanner for input


    public AdminService() {

        this.spaces = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.scanner = new Scanner(System.in);


        spaces.add(new CoworkingSpace(1, "Open Space", 1500.0, true));
        spaces.add(new CoworkingSpace(2, "Private Room", 2500.0, true));
        spaces.add(new CoworkingSpace(3, "Meeting Room", 3000.0, true));
    }


    public void addSpace() {

        String addPrompt = """
                Enter the space ID:
                """;
        System.out.println(addPrompt);
        int ID = scanner.nextInt();
        scanner.nextLine();

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


        spaces.add(new CoworkingSpace(ID, type, price, true));
        System.out.println("Coworking Space Added Successfully!");
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

        if (spaceToRemove != null) {
            spaces.remove(spaceToRemove);
            System.out.println("Coworking Space Removed Successfully!");
        } else {
            System.out.println("Coworking Space with that ID does not exist!");
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
