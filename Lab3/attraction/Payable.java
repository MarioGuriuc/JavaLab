package attraction;

import java.time.LocalDate;
import java.time.LocalTime;

// Interface used to determine if an attraction.Attraction is payable or not
public interface Payable {
	float getTicketPrice();

	void setTicketPrice(float ticketPrice);

	LocalDate getDate();

	LocalTime getTime();
}
