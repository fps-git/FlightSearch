public class TicketsRepository {
    private Ticket[] ticketsArray = new Ticket[0];

    public void add(Ticket newTicket) {
        if (findById(newTicket.getId()) != null) {
            throw new AlreadyExistsException("Element with id: " + newTicket.getId() + " already exists");
        } else {
            Ticket[] tmp = new Ticket[ticketsArray.length + 1];
            for (int i = 0; i < ticketsArray.length; i++) {
                tmp[i] = ticketsArray[i];
            }
            tmp[tmp.length - 1] = newTicket;
            ticketsArray = tmp;
        }
    }

    public Ticket[] findAll() {
        return ticketsArray;
    }

    public Ticket findById(int id) {
        for (Ticket ticket : ticketsArray) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        } else {
            Ticket[] tmp = new Ticket[ticketsArray.length - 1];
            int copyToIndex = 0;
            for (Ticket ticket : ticketsArray) {
                if (ticket.getId() != id) {
                    tmp[copyToIndex] = ticket;
                    copyToIndex++;
                }
            }
            ticketsArray = tmp;
        }
    }
}