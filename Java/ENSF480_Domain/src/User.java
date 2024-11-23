/**
 * 
 */

/**
 * 
 */
public abstract class User {
    private string name;
    private string email;
    private string address;
    private PaymentInfo paymentInfo;
    private ArrayList<TicketBooking> ticketBookings = new ArrayList<TicketBooking>();

    public User(string name, string email, string address, PaymentInfo paymentInfo) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.paymentInfo = paymentInfo;
    }

    public void bookTicket(TicketBooking ticketBooking) {
        ticketBookings.add(ticketBooking);
    }

    public void cancelTicket(TicketBooking ticketBooking) {
        ticketBookings.remove(ticketBooking);
    }

    public void showMovies() {
    }
}
