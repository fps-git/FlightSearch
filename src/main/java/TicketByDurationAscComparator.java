import java.util.Comparator;

public class TicketByDurationAscComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket o1, Ticket o2) {
        if (o1.getFlightMinutes() < o2.getFlightMinutes()) {
            return -1;
        } else if (o1.getFlightMinutes() > o2.getFlightMinutes()) {
            return 1;
        } else {
            return 0;
        }
    }
}
