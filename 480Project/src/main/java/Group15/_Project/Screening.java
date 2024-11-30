package Group15._Project;

import java.util.ArrayList;

public class Screening {
    private Theatre theatre;
    private Movie movie;
    private String screenDate;
    private String openDate;
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
