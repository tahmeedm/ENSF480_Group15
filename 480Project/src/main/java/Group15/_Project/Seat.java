package Group15._Project;

import jakarta.persistence.*;

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

    // Constructor
    public Seat(RegisteredUser occupant, float price, int seatNumber) {
        this.occupant = occupant;
        this.price = price;
        this.seatNumber = seatNumber;
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
