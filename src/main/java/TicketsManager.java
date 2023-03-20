import java.util.Arrays;

public class TicketsManager {
    private TicketsRepository repo;

    TicketsManager(TicketsRepository repo) {
        this.repo = repo;
    }

    public void add(Ticket newTicket) {
        repo.add(newTicket);
    }

    public Ticket[] findAll(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repo.findAll()) {
            if (ticket.matches(from, to)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        if (result.length == 0) {
            throw new TicketNotFoundException("По направлению из " + from + " в " + to + " билетов не найдено");
        } else {
            Arrays.sort(result);
            return result;
        }
    }

    public void removeById(int id) {
        repo.removeById(id);
    }
}