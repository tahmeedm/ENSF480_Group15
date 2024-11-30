package Group15._Project;

import jakarta.persistence.*;

@Entity
@Table(name = "theatre")
public class Theatre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "theatre_name", nullable = false)
	private String theatreName;

	@Column(name = "description")
	private String description;

	public Theatre(String theatreName, String description) {
		this.theatreName = theatreName;
		this.description = description;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
