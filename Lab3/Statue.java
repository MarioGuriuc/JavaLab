import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Statue extends Attraction implements Visitable {
	Map<DayOfWeek, Pair<LocalTime, LocalTime>> timeInterval;
	private String author;
	private String yearBuilt;

	public Statue(String title, String description, String author, String yearBuilt, DayOfWeek[] daysVisitable,
				  ArrayList<Pair<LocalTime, LocalTime>> hoursOpen) {
		this.title = title;
		this.description = description;
		this.author = author;
		this.yearBuilt = yearBuilt;
		this.setTimeInterval(daysVisitable, hoursOpen);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYearBuilt() {
		return yearBuilt;
	}

	public void setYearBuilt(String yearBuilt) {
		this.yearBuilt = yearBuilt;
	}

	public void setTimeInterval(DayOfWeek[] daysVisitable, ArrayList<Pair<LocalTime, LocalTime>> hoursOpen) {
		if (timeInterval == null) {
			timeInterval = new HashMap<>();
		}
		for (int i = 0; i < daysVisitable.length; i++) {
			timeInterval.put(daysVisitable[i], hoursOpen.get(i));
		}
	}

	public Map<DayOfWeek, Pair<LocalTime, LocalTime>> getTimeInterval() {
		return timeInterval;
	}
}
