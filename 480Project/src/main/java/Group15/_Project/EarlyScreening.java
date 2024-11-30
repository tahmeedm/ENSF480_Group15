package Group15._Project;

import java.util.ArrayList;

public class EarlyScreening extends Screening {

    private float percentRegistered;
    private String earlyDate;

    public EarlyScreening(float percentRegistered, String earlyDate, Theatre theatre, Movie movie, String screenDate,
                        String openDate, ArrayList<Seat> seatList) {
        super(theatre, movie, screenDate, openDate, seatList);
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
