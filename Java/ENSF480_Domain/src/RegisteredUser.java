
public class RegisteredUser extends User {

	private String password;
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
}
