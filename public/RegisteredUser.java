
public class RegisteredUser  {

	// private String password;
	// private String username;

		public static void main(String[] args) {
			// Ensure the arguments length is sufficient
			if (args.length < 6) {
				System.out.println("Error: Insufficient arguments provided.");
				return;
			}
	
			// Extract parameters from args
			String modeType = args[0]; // e.g., "signIn"
			String email = args[1]; // e.g., "user@example.com"
			String address = args[2]; // e.g., "123 Street"
			String paymentInfo = args[3]; // e.g., "Credit Card"
			String password = args[4]; // e.g., "password123"
			String username = args[5]; // e.g., "username123"
	
			// Build your response string
			if (modeType.equals("signIn")){
				String response = "Success!\n" +
									"email:" + email + "\n" +
									"address:" + address + "\n" +
									"paymentInfo:" + paymentInfo + "\n" +
									"password:" + password + "\n" +
									"username:" + username;
				
				System.out.println(response);
			}
			if (modeType.equals("signUp")){
				String response = "Success!\n" +
									"email:" + email + "\n" +
									"address:" + address + "\n" +
									"paymentInfo:" + paymentInfo + "\n" +
									"password:" + password + "\n" +
									"username:" + username;
				
				System.out.println(response);
			}
			else{
				System.out.println(modeType);
				System.out.println("Error: Mode Wrong.");
				return;
			}
			// String response = "Success!\n" +
			// 				  "email:" + email + "\n" +
			// 				  "address:" + address + "\n" +
			// 				  "paymentInfo:" + paymentInfo + "\n" +
			// 				  "password:" + password + "\n" +
			// 				  "username:" + username;
	
			// Print the response, which will be captured by exec() in Node.js
			
	}

	public RegisteredUser(String name, String email, String address, String paymentInfo, String password,
			String username) {
		// super(name, email, address, paymentInfo);
		// this.password = password;
		// this.username = username;
	}

	public void showSpecialMovies() {
	}

	public void payAccountFees() {
	}
}
