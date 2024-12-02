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
@Table (name = "Receipt")
public class Receipt {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID
    private Long id;  // This is the primary key for this entity

	@Embedded
	private PaymentInfo paymentInfo;

	@Column(name = "transaction_date", nullable = false)
	private String transactionDate;

	@Column(name = "total_price", nullable = false)
	private float totalPrice;

	public Receipt() {
		this.paymentInfo = null;
		this.transactionDate = null;
		this.totalPrice = 0;
	}

	public Receipt(PaymentInfo paymentInfo, String transactionDate, ArrayList<Seat> seatList, float totalPrice) {
		this.paymentInfo = paymentInfo;
		this.transactionDate = transactionDate;
		this.totalPrice = totalPrice;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
