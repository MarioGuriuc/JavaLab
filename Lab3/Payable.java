import java.time.LocalDate;
import java.time.LocalTime;

public interface Payable {
	float getTicketPrice();

	void setTicketPrice(float ticketPrice);

	LocalDate getDate();

	LocalTime getTime();
}
