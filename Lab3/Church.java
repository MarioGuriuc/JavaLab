import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Church extends Attraction implements Visitable {
	Map<DayOfWeek, Pair<LocalTime, LocalTime>> timeInterval = new HashMap<>();
	private String yearBuilt;

	public Church(String title, String description, String yearBuilt, DayOfWeek[] daysVisitable,
				  ArrayList<Pair<LocalTime, LocalTime>> hoursOpen) {
		this.title = title;
		this.description = description;
		this.yearBuilt = yearBuilt;
		this.setTimeInterval(daysVisitable, hoursOpen);
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
