package Group15._Project;

import jakarta.persistence.*;

@Entity
@Table(name = "early_screenings")
public class EarlyScreening extends Screening {

    @Column(name = "percent_registered", nullable = false)
    private float percentRegistered;

    @Column(name = "early_date", nullable = false)
    private String earlyDate;

    public EarlyScreening(float percentRegistered, String earlyDate, Theatre theatre, Movie movie, String screenDate,
                        String openDate) {
        super(theatre, movie, screenDate, openDate);
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
