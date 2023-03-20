import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketsManagerTest {

    @Test
    public void shouldThrowExceptionIfExistingId() {
        TicketsRepository repo = new TicketsRepository();
        TicketsManager manager = new TicketsManager(repo);
        Ticket ticket1 = new Ticket(1, 18_500, "SVO", "LED", 120);
        Ticket ticket2 = new Ticket(2, 19_000, "LED", "SVO", 125);
        Ticket ticket3 = new Ticket(3, 53_500, "AER", "DXB", 250);
        Ticket ticket4 = new Ticket(3, 55_700, "DXB", "AER", 250);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> manager.add(ticket4));
    }

    @Test
    public void shouldRemoveById() {
        TicketsRepository repo = new TicketsRepository();
        TicketsManager manager = new TicketsManager(repo);
        Ticket ticket1 = new Ticket(1, 18_500, "SVO", "LED", 120);
        Ticket ticket2 = new Ticket(2, 19_000, "LED", "SVO", 125);
        Ticket ticket3 = new Ticket(3, 53_500, "AER", "DXB", 250);
        Ticket ticket4 = new Ticket(4, 55_700, "DXB", "AER", 250);
        Ticket ticket5 = new Ticket(5, 26_200, "VKO", "BTS", 180);
        Ticket ticket6 = new Ticket(6, 30_400, "BTS", "VKO", 180);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        manager.removeById(2);
        manager.removeById(4);
        manager.removeById(6);

        Ticket[] expected = {ticket1, ticket3, ticket5};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionIfBadId() {
        TicketsRepository repo = new TicketsRepository();
        TicketsManager manager = new TicketsManager(repo);
        Ticket ticket1 = new Ticket(1, 18_500, "SVO", "LED", 120);
        Ticket ticket2 = new Ticket(2, 19_000, "LED", "SVO", 125);
        Ticket ticket3 = new Ticket(3, 53_500, "AER", "DXB", 250);
        Ticket ticket4 = new Ticket(4, 55_700, "DXB", "AER", 250);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);

        Assertions.assertThrows(NotFoundException.class, () -> manager.removeById(6));
    }

    @Test
    public void shouldFindAndSortByPrice() {
        TicketsRepository repo = new TicketsRepository();
        TicketsManager manager = new TicketsManager(repo);
        Ticket ticket1 = new Ticket(1, 18_500, "SVO", "LED", 120);
        Ticket ticket2 = new Ticket(2, 19_000, "LED", "SVO", 125);
        Ticket ticket3 = new Ticket(3, 53_500, "AER", "DXB", 250);
        Ticket ticket4 = new Ticket(4, 55_700, "DXB", "AER", 250);
        Ticket ticket5 = new Ticket(5, 26_200, "VKO", "BTS", 180);
        Ticket ticket6 = new Ticket(6, 30_400, "BTS", "VKO", 180);
        Ticket ticket7 = new Ticket(7, 27_900, "SVO", "LED", 120);
        Ticket ticket8 = new Ticket(8, 29_250, "LED", "SVO", 125);
        Ticket ticket9 = new Ticket(9, 14_200, "SVO", "LED", 110);
        Ticket ticket10 = new Ticket(10, 15_600, "LED", "SVO", 115);
        Ticket ticket11 = new Ticket(11, 12_990, "SVO", "LED", 105);
        Ticket ticket12 = new Ticket(12, 13_290, "LED", "SVO", 110);
        Ticket ticket13 = new Ticket(13, 21_300, "SVO", "LED", 102);
        Ticket ticket14 = new Ticket(14, 20_200, "LED", "SVO", 109);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        manager.add(ticket10);
        manager.add(ticket11);
        manager.add(ticket12);
        manager.add(ticket13);
        manager.add(ticket14);

        Ticket[] expected = {ticket11, ticket9, ticket1, ticket13, ticket7};
        Ticket[] actual = manager.findAll("SVO", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAndSortIfSamePrice() {
        TicketsRepository repo = new TicketsRepository();
        TicketsManager manager = new TicketsManager(repo);
        Ticket ticket1 = new Ticket(1, 18_500, "SVO", "LED", 120);
        Ticket ticket2 = new Ticket(2, 19_000, "LED", "SVO", 125);
        Ticket ticket3 = new Ticket(3, 53_500, "AER", "DXB", 250);
        Ticket ticket4 = new Ticket(4, 55_700, "DXB", "AER", 250);
        Ticket ticket5 = new Ticket(5, 26_200, "VKO", "BTS", 180);
        Ticket ticket6 = new Ticket(6, 30_400, "BTS", "VKO", 180);
        Ticket ticket7 = new Ticket(7, 27_900, "SVO", "LED", 120);
        Ticket ticket8 = new Ticket(8, 20_250, "LED", "SVO", 125);
        Ticket ticket9 = new Ticket(9, 14_200, "SVO", "LED", 110);
        Ticket ticket10 = new Ticket(10, 15_600, "LED", "SVO", 115);
        Ticket ticket11 = new Ticket(11, 12_990, "SVO", "LED", 105);
        Ticket ticket12 = new Ticket(12, 20_250, "LED", "SVO", 110);
        Ticket ticket13 = new Ticket(13, 21_300, "SVO", "LED", 102);
        Ticket ticket14 = new Ticket(14, 20_250, "LED", "SVO", 109);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        manager.add(ticket10);
        manager.add(ticket11);
        manager.add(ticket12);
        manager.add(ticket13);
        manager.add(ticket14);

        Ticket[] expected = {ticket10, ticket2, ticket8, ticket12, ticket14};
        Ticket[] actual = manager.findAll("LED", "SVO");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionIfFoundNothing() {
        TicketsRepository repo = new TicketsRepository();
        TicketsManager manager = new TicketsManager(repo);
        Ticket ticket1 = new Ticket(1, 18_500, "SVO", "LED", 120);
        Ticket ticket2 = new Ticket(2, 19_000, "LED", "SVO", 125);
        Ticket ticket3 = new Ticket(3, 53_500, "AER", "DXB", 250);
        Ticket ticket4 = new Ticket(4, 55_700, "DXB", "AER", 250);
        Ticket ticket5 = new Ticket(5, 26_200, "VKO", "BTS", 180);
        Ticket ticket6 = new Ticket(6, 30_400, "BTS", "VKO", 180);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Assertions.assertThrows(TicketNotFoundException.class, () -> manager.findAll("DME", "IKT"));
    }
}