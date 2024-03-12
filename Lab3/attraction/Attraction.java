package attraction;

// attraction.Attraction class represents an attraction with a title and description
public abstract class Attraction {
	protected String title;
	protected String description;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
