package src.tests.modulTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.CoworkingSpace;
import src.main.model.CoworkingStorage;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoworkingStorageTest {

    private ArrayList<CoworkingSpace> spaces;

    @BeforeEach
    void setUp() {
        spaces = new ArrayList<>();
        spaces.add(new CoworkingSpace(1, "Open Space", 1500.0, true));
        spaces.add(new CoworkingSpace(2, "Private Room", 2500.0, true));
    }

    @Test
    void testSaveSpacesToFile() {
        CoworkingStorage.saveSpacesToFile(spaces);
        assertDoesNotThrow(CoworkingStorage::loadSpacesFromFile);
    }

    @Test
    void testLoadSpacesFromFile() {
        CoworkingStorage.loadSpacesFromFile();
        ArrayList<CoworkingSpace> loadedSpaces = CoworkingStorage.loadSpacesFromFile();
        assertEquals(spaces.size(), loadedSpaces.size());

    }

}
