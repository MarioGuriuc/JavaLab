import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		DayOfWeek[] allWeekDays = { DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY };
		Pair<LocalTime, LocalTime> statueInterval = new Pair<>(LocalTime.parse("00:00"), LocalTime.parse("23:59"));
		ArrayList<Pair<LocalTime, LocalTime>> statueIntervalArray = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			statueIntervalArray.add(statueInterval);
		}
		Attraction statue = new Statue("Statue of Stefan cel Mare",
				"Statue of Stefan cel Mare from Iasi",
				"Emmanuel Fremiet", "1883", allWeekDays, statueIntervalArray);

		Pair<LocalTime, LocalTime> churchInterval = new Pair<>(LocalTime.parse("06:00"), LocalTime.parse("21:00"));
		ArrayList<Pair<LocalTime, LocalTime>> churchIntervalArray = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			churchIntervalArray.add(churchInterval);
		}
		Attraction church = new Church("Catedrala Mitropolitana",
				"Catedrala Mitropolitana from Iasi", "1887",
				allWeekDays, churchIntervalArray);

		Attraction concert = new Concert("Oscar Tiki Nights",
				"Concert Oscar Iasi Tiki Nights",
				"Oscar", 35, LocalDate.parse("2024-08-28"), LocalTime.parse("23:00"));
		ArrayList<Attraction> attractions = new ArrayList<>();
		attractions.add(statue);
		attractions.add(church);
		attractions.add(concert);
		Trip trip = new Trip("Iasi", attractions);
		TravelPlan travelPlan = new TravelPlan(trip);
		System.out.println(travelPlan);
	}
}
