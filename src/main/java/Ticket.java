import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Ticket implements Comparable<Ticket> {
    private int id;
    private int price;
    private String from;
    private String to;
    private int flightMinutes;

    @Override
    public int compareTo(Ticket o) {
        if (this.price < o.price) {
            return -1;
        } else if (this.price > o.price) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean matches(String from, String to) {
        return (getFrom().contains(from) & getTo().contains(to));
    }
}