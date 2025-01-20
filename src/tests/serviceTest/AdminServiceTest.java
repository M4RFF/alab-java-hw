package src.tests.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.service.AdminService;
import src.main.model.CoworkingSpace;
import src.main.model.Reservations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AdminServiceTest {

    private AdminService<Reservations> adminService;
    private ArrayList<CoworkingSpace> spaces;

    @BeforeEach
    void setUp() {
        spaces = new ArrayList<>();
        spaces.add(new CoworkingSpace(1, "Open Space", 1500.0, true));
        spaces.add(new CoworkingSpace(2, "Private Room", 2500.0, true));
        adminService = new AdminService<>(spaces);
    }

    @Test
    void testAddSpace() {
        CoworkingSpace space = new CoworkingSpace(3, "Meeting Room", 3500.0, true);
        spaces.add(space);
        assertTrue(spaces.contains(space));
    }

    @Test
    void testRemoveSpace() {
        CoworkingSpace spaceToRemove = spaces.getFirst();
        spaces.remove(spaceToRemove);
        assertFalse(spaces.contains(spaceToRemove));
    }

    @Test
    void testViewReservations() {
        assertDoesNotThrow(() -> adminService.viewReservations());
    }

}
