package Group15._Project;
import java.util.ArrayList;

public class Receipt {
	private PaymentInfo paymentInfo;
	private String transactionDate;
	private ArrayList<Seat> seatList;
	private float totalPrice;

	public Receipt(PaymentInfo paymentInfo, String transactionDate, ArrayList<Seat> seatList, float totalPrice) {
		this.paymentInfo = paymentInfo;
		this.transactionDate = transactionDate;
		this.seatList = seatList;
		this.totalPrice = totalPrice;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public ArrayList<Seat> getSeatList() {
		return seatList;
	}

	public float getTotalPrice() {
		return totalPrice;
	}
}
