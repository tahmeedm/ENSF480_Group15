package Group15._Project;
public class PaymentInfo {
	private String cardNumber;
	private int cvv;
	private String expiryDate;

	public PaymentInfo(String cardNumber, int cvv, String expiryDate) {
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public int getCvv() {
		return cvv;
	}

	public String getExpiryDate() {
		return expiryDate;
	}
}
