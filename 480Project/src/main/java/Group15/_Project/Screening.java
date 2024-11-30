package Group15._Project;

import java.util.ArrayList;

import jakarta.persistence.*;

@Entity
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

    public Movie getMovie() {
        return movie;
    }

    public String getScreenDate() {
        return screenDate;
    }

    public String getOpenDate() {
        return openDate;
    }

    public ArrayList<Seat> getSeatList() {
        return seatList;
    }

    public void addSeat(Seat seat) {
        seatList.add(seat);
    }

    public void removeSeat(Seat seat) {
        seatList.remove(seat);
    }

}
