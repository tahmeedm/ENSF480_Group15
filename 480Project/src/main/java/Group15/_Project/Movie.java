package Group15._Project;
import java.util.ArrayList;

import jakarta.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "movie_name", nullable = false)
	private String name;

	@Column(name = "movie_description")
	private String description;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private ArrayList<Screening> screenings = new ArrayList<Screening>();

	@Column(name = "release_date", nullable = false)
	private String releaseDate;

	public Movie(String name, String description, int id, String releaseDate) {
		this.name = name;
		this.description = description;
		this.id = id;
		this.releaseDate = releaseDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public ArrayList<Screening> getScreenings() {
		return screenings;
	}

	public void setScreenings(ArrayList<Screening> screenings) {
		this.screenings = screenings;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void addScreening(Screening screening) {
		screenings.add(screening);
	}

	public void removeScreening(Screening screening) {
		screenings.remove(screening);
	}

}
