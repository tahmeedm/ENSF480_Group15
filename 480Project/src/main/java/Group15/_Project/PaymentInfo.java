package Group15._Project;

import jakarta.persistence.Embeddable;

@Embeddable
public class PaymentInfo {
	private String cardNumber;
	private int cvv;
	private String expiryDate;

	// Default constructor
	public PaymentInfo() {
		this.cardNumber = null;
		this.cvv = 0;
		this.expiryDate = null;
	}

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
