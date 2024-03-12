package trip;

import attraction.Attraction;
import attraction.Payable;
import attraction.Visitable;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
// trip.Trip class represents a trip to a city with a list of attractions
public class Trip {
	private String cityName;
	private List<Attraction> attractions;

	public Trip(String cityName, List<Attraction> attractions) {
		this.cityName = cityName;
		this.attractions = new ArrayList<>(attractions);
	}
	// Prints visitable attractions for a specific day of the week
	public void printVisitable(DayOfWeek dayOfWeek) {
		ArrayList<Visitable> visitables = new ArrayList<>();
		for (var attraction : attractions) {
			if (attraction instanceof Visitable && !(attraction instanceof Payable)) {
				visitables.add((Visitable) attraction);
			}
		}
		for (int i = 0; i < visitables.size(); i++) {
			for (int j = i + 1; j < visitables.size(); j++) {
				Visitable visitableI = visitables.get(i);
				Visitable visitableJ = visitables.get(j);
				if (visitableI.getOpeningHour(dayOfWeek).isAfter(visitableJ.getOpeningHour(dayOfWeek))) {
					visitables.set(i, visitableJ);
					visitables.set(j, visitableI);
				}
			}
		}
		StringBuilder result = new StringBuilder();
		for (var visitable : visitables) {
			result.append(((Attraction) visitable).getTitle()).append('\n');
			result.append(((Attraction) visitable).getDescription()).append('\n');
			result.append("Visiting hour: ");
			result.append(visitable.getOpeningHour(dayOfWeek).getHour()).append("\n\n");
		}
		System.out.println(result);
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<Attraction> getAttractions() {
		return attractions;
	}

	public void setAttractions(List<Attraction> attractions) {
		this.attractions = attractions;
	}
}
