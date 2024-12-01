package Group15._Project;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@jakarta.persistence.Entity
@jakarta.persistence.Inheritance(strategy = jakarta.persistence.InheritanceType.JOINED)

public abstract class User {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(name = "email", nullable = true)
    private String email;
    
    @Embedded
    @Column(nullable = true)
    private PaymentInfo paymentInfo;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketBooking> ticketBookings = new ArrayList<TicketBooking>();

    public User(String email, PaymentInfo paymentInfo) {
        this.email = email;
        this.paymentInfo = paymentInfo;
    }

    public void bookTicket(TicketBooking ticketBooking) {
        ticketBookings.add(ticketBooking);
    }

    public void cancelTicket(TicketBooking ticketBooking) {
        ticketBookings.remove(ticketBooking);
    }

    public void showMovies() {
    }

    public String getEmail() {
        return email;
    }   

    public void setEmail(String email) {
        this.email = email;
    }   

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public List<TicketBooking> getTicketBookings() {
        return ticketBookings;
    }

    public void setTicketBookings(ArrayList<TicketBooking> ticketBookings) {
        this.ticketBookings = ticketBookings;
    }
}
