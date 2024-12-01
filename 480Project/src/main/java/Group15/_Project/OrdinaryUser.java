package Group15._Project;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OrdinaryUser")
public class OrdinaryUser extends User{

	@Column(name = "admin_fee_expiration_date", nullable= true)
	private String adminFeeExpirationDate;

	@Column(name = "has_admin_fee", nullable = false)
	private boolean  hasAdminFee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID
    private Long id;  // This is the primary key for this entity

	public OrdinaryUser() {
		super(null, null);
		this.adminFeeExpirationDate = "";
		this.hasAdminFee = false;
	}

	public OrdinaryUser(String adminFeeExpirationDate, boolean hasAdminFee, String email, PaymentInfo paymentInfo) {
		super(email, paymentInfo);
		this.adminFeeExpirationDate = adminFeeExpirationDate;
		this.hasAdminFee = hasAdminFee;
		if(!hasAdminFee){
			this.hasAdminFee = false;
		}
		
	}

	public String getAdminFeeExpirationDate() {
		return adminFeeExpirationDate;
	}

	public void setAdminFeeExpirationDate(String adminFeeExpirationDate) {
		this.adminFeeExpirationDate = adminFeeExpirationDate;
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
