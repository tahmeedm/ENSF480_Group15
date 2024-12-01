package Group15._Project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "registered_user_id")
    private RegisteredUser occupant;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "seat_number", nullable = false)
    private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "screening_id", nullable = false)
    private Screening screening;

    // Default constructor
    public Seat() {
        this.occupant = null;
        this.price = 0;
        this.seatNumber = 0;
        this.screening = null;
    }

    // Constructor
    public Seat(RegisteredUser occupant, float price, int seatNumber, Screening screening) {
        this.occupant = occupant;
        this.price = price;
        this.seatNumber = seatNumber;
        this.screening = screening;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegisteredUser getOccupant() {
        return occupant;
    }

    public void setOccupant(RegisteredUser occupant) {
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
