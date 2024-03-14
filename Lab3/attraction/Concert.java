package attraction;

import java.time.LocalDate;
import java.time.LocalTime;

// Concert class that represents a concert inherited from the Attraction class which also implements the Payable
// interface
public class Concert extends Attraction implements Payable {

	private String artistName;
	private float ticketPrice;
	private LocalDate date;
	private LocalTime time;

	public Concert(String title, String description, String artistName, float ticketPrice, LocalDate date,
				   LocalTime time) {
		if (ticketPrice >= 0) {
			this.ticketPrice = ticketPrice;
		}
		this.title = title;
		this.description = description;
		this.artistName = artistName;
		this.date = date;
		this.time = time;
	}

	@Override
	public float getTicketPrice() {
		return ticketPrice;
	}

	@Override
	public void setTicketPrice(float ticketPrice) {
		if (ticketPrice >= 0) {
			this.ticketPrice = ticketPrice;
		}
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public LocalTime getTime() {
		return this.time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
}
