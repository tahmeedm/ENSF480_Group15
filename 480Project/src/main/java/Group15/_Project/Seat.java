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
    @JoinColumn(name = "registered_user_id", nullable = true)
    private RegisteredUser registeredUser;

    @Column(name = "ordinary_user_id", nullable = true)
    private String ordinaryUserId;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "seat_number", nullable = false)
    private int seatNumber;

    // Constructor
    public Seat(RegisteredUser registeredUser, String ordinaryUserId, float price, int seatNumber) {
        this.registeredUser = registeredUser;
        this.ordinaryUserId = ordinaryUserId;
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
}
