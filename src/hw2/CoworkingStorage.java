package src.hw2;

import src.hw1.CoworkingSpace;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

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

        // Using Optional For File Loading
        Optional<File> file = Optional.ofNullable(new File(FILE_NAME));

        file.filter(File::exists)
                .ifPresentOrElse( // Executes file loading logic, if not, then handles the absence of the file
                        f -> {
                            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                                System.out.println("Spaces Loaded successfully!");
                            } catch (IOException e) {
                                System.out.println("Error loading spaces: " + e.getMessage());
                            }
                        },
                        () -> System.out.println("No existing space file found!")
                );

        return spaces;
    }

    public static <T> void printArrayList(ArrayList<T> list) {
        if (list.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            list.forEach(System.out::println);
        }
    }

    public static <K, V> void printMap(HashMap<K, V> map) {
        for (var entry : map.entrySet()) {
            System.out.println("ID: " + entry.getKey() + " -> " + entry.getValue());
        }
    }
}
