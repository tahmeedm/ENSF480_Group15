
public class EarlyScreening extends Screening {

	private float percentRegistered;
	private String earlyDate;

	public EarlyScreening(float percentRegistered, String earlyDate, Theatre theatre, String room, String screenDate,
			String openDate) {
		super(theatre, room, screenDate, openDate);
		this.percentRegistered = percentRegistered;
		this.earlyDate = earlyDate;
	}

	public float getPercentRegistered() {
		return percentRegistered;
	}

	public String getEarlyDate() {
		return earlyDate;
	}

	public void setPercentRegistered(float percentRegistered) {
		this.percentRegistered = percentRegistered;
	}

	public void setEarlyDate(String earlyDate) {
		this.earlyDate = earlyDate;
	}

}
