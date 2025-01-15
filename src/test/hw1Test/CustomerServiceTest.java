package src.test.hw1Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.hw1.CoworkingSpace;
import src.hw1.CustomerService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    private CustomerService customerService;
//    private ArrayList<CoworkingSpace> spaces;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService();
    }

    @Test
    void testFindSpace() {
        ArrayList<CoworkingSpace> spaces = new ArrayList<>();
        spaces.add(new CoworkingSpace(1, "Open Space", 1500.0, true));
        spaces.add(new CoworkingSpace(1, "Private Room", 2500.0, true));
        customerService.findSpace();
        assertNotNull(spaces);
    }

    @Test
    void testMakeReservation() {
        ArrayList<CoworkingSpace> spaces = new ArrayList<>();
        spaces.add(new CoworkingSpace(1, "Open Space", 1500.0, true));
        customerService.makeReservation();
        assertEquals(1, spaces.size());
    }

    @Test
    void testCancelReservation() {
        customerService.cancelReservation();
        assertDoesNotThrow(
                () -> customerService.cancelReservation()
        );
    }
}
