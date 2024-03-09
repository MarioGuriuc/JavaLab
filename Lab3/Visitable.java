import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;

public interface Visitable {
	default LocalTime getOpeningHour(DayOfWeek dayOfWeek) {
		return this.getTimeInterval().get(dayOfWeek).getFirst();
	}

	Map<DayOfWeek, Pair<LocalTime, LocalTime>> getTimeInterval();
}
