package Group15._Project;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "registered_user_id", nullable = true)
    private RegisteredUser registeredUser;

    @Column(name = "ordinary_user_id", nullable = true)
    private String ordinaryUserId;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "seat_number", nullable = false)
    private int seatNumber;

    @JsonIgnore
    @ManyToOne
	@JoinColumn(name = "screening_id", nullable = false)
	private Screening screening;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ticket_booking_id", nullable=true)
    private TicketBooking ticketBooking;

    // Default constructor
    public Seat() {
        this.occupant = null;
        this.price = 0;
        this.seatNumber = 0;
        this.screening = null;
        this.ticketBooking = null;
    }

    // Constructor
    public Seat(RegisteredUser occupant, float price, int seatNumber, Screening screening, TicketBooking ticketBooking) {
        this.occupant = occupant;
        this.price = price;
        this.seatNumber = seatNumber;
        this.screening = screening;
        this.ticketBooking = ticketBooking;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(RegisteredUser registeredUser) {
        this.registeredUser = registeredUser;
    }

    public String getOrdinaryUserId() {
        return ordinaryUserId;
    }

    public void setOrdinaryUserId(String ordinaryUserId) {
        this.ordinaryUserId = ordinaryUserId;
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

    public TicketBooking getTicketBooking() {
        return ticketBooking;
    }

    public void setTicketBooking(TicketBooking ticketBooking) {
        this.ticketBooking = ticketBooking;
    }

    // isAvailable
    public boolean isAvailable() {
        return this.occupant == null;
    }

    // getScreening
    public Screening getScreening() {
        return this.screening;
    }

    // setIsAvailable
    public void setIsAvailable(boolean isAvailable) {
        this.occupant = isAvailable ? null : this.occupant;
    }

    // setScreening
    public void setScreening(Screening screening) {
        this.screening = screening;
    }
}