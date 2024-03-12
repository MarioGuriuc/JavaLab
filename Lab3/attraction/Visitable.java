package attraction;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;

// Interface used to determine if an attraction.Attraction is visitable or not
public interface Visitable {
	default LocalTime getOpeningHour(DayOfWeek dayOfWeek) {
		return this.getTimeInterval().get(dayOfWeek).getFirst();
	}

	Map<DayOfWeek, Pair<LocalTime, LocalTime>> getTimeInterval();
}
