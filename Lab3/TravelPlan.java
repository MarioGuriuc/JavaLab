import java.time.DayOfWeek;

public class TravelPlan {
	private Trip trip;
	private StringBuilder travelPlan;

	public TravelPlan(Trip trip) {
		this.trip = trip;
	}

	@Override
	public String toString() {
		if (travelPlan != null) {
			return travelPlan.toString();
		}
		DayOfWeek[] allWeekDays = { DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY };
		travelPlan = new StringBuilder("The tourist can visit these attractions at the specified time " +
				"and day of the week or date: \n");
		for (var attraction : trip.getAttractions()) {
			travelPlan.append("Title: ").append(attraction.getTitle()).append('\n');
			travelPlan.append("Description: ").append(attraction.getDescription()).append('\n');
			if (attraction instanceof Visitable visitable) {
				var timeInterval = visitable.getTimeInterval();
				for (var day : allWeekDays) {
					travelPlan.append(day).append(" ");
					travelPlan.append(timeInterval.get(day).getFirst()).append(" to ");
					travelPlan.append(timeInterval.get(day).getSecond());
					travelPlan.append('\n');
				}
			}
			else if (attraction instanceof Payable payable) {
				travelPlan.append("YYYY/MM/DD: ");
				travelPlan.append(payable.getDate()).append(" at ").append(payable.getTime());
				travelPlan.append('\n');
			}
			travelPlan.append('\n');
		}
		return travelPlan.toString();
	}
}
