package attraction;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;

// Interface used to determine if an Attraction is visitable or not
public interface Visitable {
	default LocalTime getOpeningHour(DayOfWeek dayOfWeek) {
		if (this.getTimeInterval().containsKey(dayOfWeek)) {
			return this.getTimeInterval().get(dayOfWeek).getOpeningHour();
		}
		return null;
	}

	Map<DayOfWeek, Pair<LocalTime, LocalTime>> getTimeInterval();
}
