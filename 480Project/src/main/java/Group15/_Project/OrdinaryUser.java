package Group15._Project;
public class OrdinaryUser extends User {

	private String adminFeeExipirationDate;
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
