package Group15._Project;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket_booking")
public class TicketBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "screening_id", nullable = false)
	private Screening screening;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "receipt_id", nullable = false)
	private Receipt receipt;

	public TicketBooking(Screening screening, Receipt receipt) {
		this.screening = screening;
		this.receipt = receipt;
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

}
