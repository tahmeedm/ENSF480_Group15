package Group15._Project;

public abstract class ScreeningDecorator extends Screening {
    protected Screening wrappedScreening;

    public ScreeningDecorator(Screening wrappedScreening) {
        super(wrappedScreening.getTheatre(), wrappedScreening.getRoom(),
            wrappedScreening.getScreenDate(), wrappedScreening.getOpenDate());
        this.wrappedScreening = wrappedScreening;
    }

    @Override
    public String getScreeningDetails() {
        return wrappedScreening.getScreeningDetails();
    }
}
