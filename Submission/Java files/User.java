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
import jakarta.persistence.FetchType;

@jakarta.persistence.Entity
@jakarta.persistence.Inheritance(strategy = jakarta.persistence.InheritanceType.JOINED)

public abstract class User {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(name = "name", nullable = true)
    private String name;
    @Column(name = "email", nullable = true, unique = true)
    private String email;
    @Column(name = "address", nullable = true)
    private String address;
    
    @Embedded
    @Column(nullable = true)
    private PaymentInfo paymentInfo;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<TicketBooking> ticketBookings = new ArrayList<TicketBooking>();

    public User() {
        // Default constructor
        this.name = null;
        this.email = "";
        this.address = null;
        this.paymentInfo = null;
    }

    public User(String name, String email, String address, PaymentInfo paymentInfo) {
        this.name = name;
        this.email = email;
        this.address = address;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }   

    public String getEmail() {
        return email;
    }   

    public void setEmail(String email) {
        this.email = email;
    }   

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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