package Group15._Project;
public class Theatre {
	private String theatreName;
	private String description;
	private int id;

	public Theatre(String theatreName, String description, int id) {
		this.theatreName = theatreName;
		this.description = description;
		this.id = id;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
