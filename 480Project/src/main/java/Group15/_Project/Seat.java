package Group15._Project;
public class Seat {
	private User occupant;
	private float price;
	private int seatNumber;

	public Seat(User occupant, float price, int seatNumber) {
		this.occupant = occupant;
		this.price = price;
		this.seatNumber = seatNumber;
	}

	public User getOccupant() {
		return occupant;
	}

	public void setOccupant(User occupant) {
		this.occupant = occupant;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
}
