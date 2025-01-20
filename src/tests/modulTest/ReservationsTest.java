package src.tests.modulTest;

import org.junit.jupiter.api.Test;
import src.main.model.Reservations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationsTest {

    @Test
    void testDisplayInfo() {
        Reservations reservations = new Reservations(1, 1, "Maksim Shcherbakov", "2025-01-14", "13:00", "15:00");
        String infoToGet = "ReservationID: 1, SpaceID: 1, CustomerName: Maksim Shcherbakov, Date: 2025-01-14, StartTime: 13:00, EndTime: 15:00";
        assertEquals(infoToGet, reservations.displayInfo());
    }

    @Test
    void testGetReservationID() {
        Reservations reservation = new Reservations(1, 1, "Maksim Shcherbakov", "2025-01-14", "13:00", "15:00");
        assertEquals(1, reservation.getReservationID());
    }
}
