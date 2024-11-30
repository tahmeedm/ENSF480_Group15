package Group15._Project;

import jakarta.persistence.*;

@Entity
@Table(name = "OrdinaryUser")
public class OrdinaryUser extends User {

	@Column(name = "admin_fee_expiration_date")
	private String adminFeeExipirationDate;

	@Column(name = "has_admin_fee", nullable = false)
	private boolean  hasAdminFee;

	public OrdinaryUser(String name, String email, String address, PaymentInfo paymentInfo,
			String adminFeeExipirationDate, boolean hasAdminFee) {
		super(name, email, address, paymentInfo);
		this.adminFeeExipirationDate = adminFeeExipirationDate;
		this.hasAdminFee = hasAdminFee;
	}

	public String getAdminFeeExipirationDate() {
		return adminFeeExipirationDate;
	}

	public void setAdminFeeExipirationDate(String adminFeeExipirationDate) {
		this.adminFeeExipirationDate = adminFeeExipirationDate;
	}

	public boolean isHasAdminFee() {
		return hasAdminFee;
	}

	public void setHasAdminFee(boolean hasAdminFee) {
		this.hasAdminFee = hasAdminFee;
	}

}
