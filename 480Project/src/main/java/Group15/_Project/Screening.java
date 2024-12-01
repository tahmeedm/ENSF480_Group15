package Group15._Project;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "screenings")
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = true)
    private Theatre theatre;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable =  true)
    private Movie movie;

    @Column(name = "screen_date", nullable =  true)
    private String screenDate;

    @Column(name = "open_date", nullable =  true)
    private String openDate;

    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Seat> screeningSeatList;

    //default constructor
    public Screening() {
        this.screeningSeatList = new ArrayList<>();
    }

    // Constructor
    public Screening(Theatre theatre, Movie movie, String screenDate, String openDate , ArrayList<Seat> seatList) {
        this.theatre = theatre;
        this.movie = movie;
        this.screenDate = screenDate;
        this.openDate = openDate;
        this.screeningSeatList = seatList;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getScreenDate() {
        return screenDate;
    }

    public String getOpenDate() {
        return openDate;
    }

    public List<Seat> getScreeningSeatList() {
        return screeningSeatList;
    }

    public void addSeat(Seat seat) {
        screeningSeatList.add(seat);
    }

    public void removeSeat(Seat seat) {
        screeningSeatList.remove(seat);
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setScreenDate(String screenDate) {
        this.screenDate = screenDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public void setScreeningSeatList(List<Seat> screeningSeatList) {
        this.screeningSeatList = screeningSeatList;
    }

}
