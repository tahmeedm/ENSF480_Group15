package Group15._Project;

import jakarta.persistence.*;

@Entity
@Table(name = "RegisteredUser")
public class RegisteredUser extends User {

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	public RegisteredUser(String name, String email, String address, PaymentInfo paymentInfo, String password,
			String username) {
		super(name, email, address, paymentInfo);
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
}
