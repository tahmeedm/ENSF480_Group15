package Group15._Project;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket_booking")
public class TicketBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "screening_id", nullable = false)
	private Screening screening;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "receipt_id", nullable = false)
	private Receipt receipt;

	@OneToMany(mappedBy = "ticketBooking", cascade = CascadeType.ALL)
	private List<Seat> seats;

	public TicketBooking() {
		this.screening = null;
		this.receipt = null;
		this.seats = null;
	}

	public TicketBooking(Screening screening, Receipt receipt, List<Seat> seats) {
		this.screening = screening;
		this.receipt = receipt;
		this.seats = seats;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Screening getScreening() {
		return screening;
	}

	public void setScreening(Screening screening) {
		this.screening = screening;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

}
