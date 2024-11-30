package Group15._Project;
import java.util.ArrayList;

import jakarta.persistence.*;

@Entity
@Table (name = "Receipt")
public class Receipt {

	@Embedded
	private PaymentInfo paymentInfo;

	@Column(name = "transaction_date", nullable = false)
	private String transactionDate;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private ArrayList<Seat> seatList;

	@Column(name = "total_price", nullable = false)
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
