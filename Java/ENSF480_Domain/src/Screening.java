import java.util.ArrayList;

public abstract class Screening {
	private Theatre theatre;
	private String room;
	private String screenDate;
	private String openDate;
	private ArrayList<Seat> seatList = new ArrayList<Seat>();

	public Screening(Theatre theatre, String room, String screenDate, String openDate) {
		this.theatre = theatre;
		this.room = room;
		this.screenDate = screenDate;
		this.openDate = openDate;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public String getRoom() {
		return room;
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

	public void setSeatList(ArrayList<Seat> seatList) {
		this.seatList = seatList;
	}

	public void addSeat(Seat seat) {
		seatList.add(seat);
	}

	public void removeSeat(Seat seat) {
		seatList.remove(seat);
	}
}
