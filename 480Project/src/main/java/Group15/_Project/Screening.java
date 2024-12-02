package Group15._Project;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "screenings")
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @Column(name = "screen_date", nullable = false)
    private String screenDate;

    @Column(name = "open_date", nullable = false)
    private String openDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Seat> seatList;

    //default constructor
    public Screening() {
        this.seatList = new ArrayList<>();
    }

    // Constructor
    public Screening(Theatre theatre, Movie movie, String screenDate, String openDate , ArrayList<Seat> seatList) {
        this.theatre = theatre;
        this.movie = movie;
        this.screenDate = screenDate;
        this.openDate = openDate;
        this.seatList = seatList;
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

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getScreenDate() {
        return screenDate;
    }

    public void setScreenDate(String screenDate) {
        this.screenDate = screenDate;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public List<Seat> getScreeningSeatList() {
        return screeningSeatList;
    }

    public void setScreeningSeatList(List<Seat> screeningSeatList) {
        this.screeningSeatList = screeningSeatList;
    }

    public void setSeatList(ArrayList<Seat> seatList) {
        this.screeningSeatList = seatList;
    }

    public void addSeat(Seat seat) {
        seatList.add(seat);
    }

    public void removeSeat(Seat seat) {
        screeningSeatList.remove(seat);
    }

    @Override
    public String toString() {
        return "Screening{" +
                "id=" + id +
                ", theatre=" + theatre +
                ", movie=" + movie +
                ", screenDate='" + screenDate + '\'' +
                ", openDate='" + openDate + '\'' +
                '}';
    }
}