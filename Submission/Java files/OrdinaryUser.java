package Group15._Project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OrdinaryUser")
public class OrdinaryUser extends User {

	@Column(name = "admin_fee_expiration_date")
	private String adminFeeExipirationDate;

	@Column(name = "has_admin_fee", nullable = false)
	private boolean  hasAdminFee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID
    private Long id;  // This is the primary key for this entity

	public OrdinaryUser() {
		super(null, null, null, null);
		this.adminFeeExipirationDate = "";
		this.hasAdminFee = false;
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
