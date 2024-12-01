package Group15._Project;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "RegisteredUser")
public class RegisteredUser extends User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID (for databases like MySQL)
    private Long id;  // Primary key field

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "name", nullable = false, unique= false)
	private String name;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "address", nullable = true)
	private String address;

	@OneToMany(mappedBy = "registeredUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TicketBooking> ticketBookings = new ArrayList<TicketBooking>();

	public RegisteredUser() {
        // Default constructor
		super(null, null);
        this.username = "";
		this.password = "";
		this.address = "";
	}

	public RegisteredUser(String name, String address, String password, String username, String email, PaymentInfo paymentInfo) {
		// Constructor
		super(email, paymentInfo);
		this.name = name;
		this.address = address;
		this.password = password;
		this.username = username;
	}

	public void showSpecialMovies() {
	}

	public void payAccountFees() {
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }   

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<TicketBooking> getTicketBookings() {
        return ticketBookings;
    }

    public void setTicketBookings(ArrayList<TicketBooking> ticketBookings) {
        this.ticketBookings = ticketBookings;
    }
}
