package Group15._Project;

public class EarlyScreeningDecorator extends ScreeningDecorator {

    private float percentRegistered;
    private String earlyDate;

    public EarlyScreeningDecorator(Screening wrappedScreening, float percentRegistered, String earlyDate) {
        super(wrappedScreening);
        this.percentRegistered = percentRegistered;
        this.earlyDate = earlyDate;
    }

    @Override
    public String getScreeningDetails() {
        // Adding early screening specific information
        return super.getScreeningDetails() + " Early registration available: " + percentRegistered + "% registered by " + earlyDate + ".";
    }
}
