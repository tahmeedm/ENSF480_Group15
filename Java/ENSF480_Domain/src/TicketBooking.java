
public class TicketBooking {
	private Screening screening;
	private Receipt receipt;

	public TicketBooking(Screening screening, Receipt receipt) {
		this.screening = screening;
		this.receipt = receipt;
	}

	public Screening getScreening() {
		return screening;
	}

	public void setScreening(Screening screening) {
		this.screening = screening;
	}

}
