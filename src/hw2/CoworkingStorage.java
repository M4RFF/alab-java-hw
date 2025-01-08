package hw2;

import hw1.CoworkingSpace;
import java.io.*;
import java.util.ArrayList;

public class CoworkingStorage {
    private static final String FILE_NAME = "coworking_spaces.txt";

    // Save coworking spaces to a file
    public static void saveSpacesToFile(ArrayList<CoworkingSpace> newSpaces) {

        /*
            Loads existing spaces from the file
            Going though the list and trying to find duplicates by IDs
            If there's no duplicates, adding to the list
         */
        ArrayList<CoworkingSpace> existingSpaces = loadSpacesFromFile();

        for (CoworkingSpace newSpace : newSpaces) {
            boolean exists = false;
            for (CoworkingSpace existingSpace : existingSpaces) {
                if (existingSpace.getSpaceID() == newSpace.getSpaceID()) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                existingSpaces.add(newSpace);
            }
        }

        /*
           Write the updated list back to the file
           This method opens the file using bw, going though the list of coworking spaces and write it down
           If file already exists it overwrites
           Each space is written as a new line in a format on the line 35
           If an IOException happens, the error prints
         */
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (CoworkingSpace space : existingSpaces) {
                bw.write(space.getSpaceID() + "," + space.getType() + "," + space.getPrice() + "," + space.isAvailable());
                bw.newLine();
            }
            System.out.println("Spaces saved successfully! (" + existingSpaces.size() + " spaces)");
        } catch (IOException e) {
            System.out.println("Error saving spaces: " + e.getMessage());
        }
    }

    // Load coworking spaces from a file
    public static ArrayList<CoworkingSpace> loadSpacesFromFile() {
        ArrayList<CoworkingSpace> spaces = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 4) {
                        spaces.add(new CoworkingSpace(
                                Integer.parseInt(data[0]),
                                data[1],
                                Double.parseDouble(data[2]),
                                Boolean.parseBoolean(data[3])
                        ));
                    }
                }
                System.out.println("Spaces loaded successfully!");
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error loading spaces: " + e.getMessage());
            }
        } else {
            System.out.println("No existing space file found!");
        }
        return spaces;
    }
}
