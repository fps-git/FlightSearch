import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Ticket {
    private int id;
    private int price;
    private String from;
    private String to;
    private int flightMinutes;

    public boolean matches(String from, String to) {
        return (getFrom().contains(from) & getTo().contains(to));
    }
}