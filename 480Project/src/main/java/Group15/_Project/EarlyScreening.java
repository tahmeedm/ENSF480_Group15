package Group15._Project;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("Early")
public class EarlyScreening extends Screening {

    @Column(name = "percent_registered", nullable = false)
    private float percentRegistered;

    @Column(name = "early_date", nullable = false)
    private String earlyDate;

    public EarlyScreening() {
        // Default constructor
        super(null, null, null, null, null);
        this.percentRegistered = 0;
        this.earlyDate = null;
    }

    public EarlyScreening(float percentRegistered, String earlyDate, Theatre theatre, Movie movie, String screenDate,
                        String openDate, ArrayList<Seat> seatList, String dtype) {
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
