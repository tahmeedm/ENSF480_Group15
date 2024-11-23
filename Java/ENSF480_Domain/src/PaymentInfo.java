
public class PaymentInfo {
	private int cardNumber;
	private int cvv;
	private String expiryDate;

	public PaymentInfo(int cardNumber, int cvv, String expiryDate) {
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public int getCvv() {
		return cvv;
	}

	public String getExpiryDate() {
		return expiryDate;
	}
}
