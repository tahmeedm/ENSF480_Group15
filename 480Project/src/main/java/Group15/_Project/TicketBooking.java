package Group15._Project;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket_booking")
public class TicketBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "registered_user_id", nullable = true)
	private RegisteredUser registeredUser;

	@ManyToOne
	@JoinColumn(name = "screening_id", nullable = false)
	private Screening screening;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "receipt_id", nullable = false)
	private Receipt receipt;

	public TicketBooking(RegisteredUser registeredUser, Screening screening, Receipt receipt) {
		this.registeredUser = registeredUser;
		this.screening = screening;
		this.receipt = receipt;
	}

	public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(RegisteredUser registeredUser) {
        this.registeredUser = registeredUser;
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

}
