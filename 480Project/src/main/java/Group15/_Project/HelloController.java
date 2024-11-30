package Group15._Project;

import java.util.ArrayList;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@RestController
public class HelloController {

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Apply CORS to all endpoints
            .allowedOrigins("http://localhost:3000")  // Allow requests from your frontend (adjust for your frontend's URL)
            .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow the relevant HTTP methods
            .allowedHeaders("*")  // Allow any headers
            .allowCredentials(true);  // Allow cookies if needed
    }
}

    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("Hello from Java in TestProgram!");
        return "Hello, Spring Boot!";
    }

    @GetMapping("/test")
    public String test(@RequestParam(required = false) String[] args) {
        if (args != null && args.length > 0) {
            System.out.print("This is user's input: ");
            for (String arg : args) {
                System.out.print(arg + " ");
            }
            String convertedString = String.join(" ", args);  // Concatenate the arguments
            return convertedString;  // Return the concatenated string
        } else {
            return "No input received.";  // Default message if no args were provided
        }
    }

    @PostMapping("/fetchSeats")
    public ArrayList<Seat> getSeats(@RequestBody Screening arg) {
        System.out.println("Screening info: " + arg);

        // Get Seats from some service or API (placeholder logic for now)
        ArrayList<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            seats.add(new Seat(null, 15, i));  // Sample seat creation
        }

        System.out.println("Seats: " + seats);
        return seats;
    }

    @GetMapping("/fetchScreenings")
    public ArrayList<Screening> getFilmScreenings() {
        

        // Placeholder for movies
        // Theatre(String theatreName, String description, int id)
        ArrayList<Theatre> theatres = new ArrayList<>();
        theatres.add (new Theatre("Theatre 1", "Description 1", 1));
        theatres.add (new Theatre("Theatre 2", "Description 2", 2));
        theatres.add (new Theatre("Theatre 3", "Description 3", 3));

        // Movie(String name, String description, int id, String releaseDate)
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add (new Movie("Movie 1", "Description 1", 1, "2023-01-01"));
        movies.add (new Movie("Movie 2", "Description 2", 2, "2023-02-02"));
        movies.add (new Movie("Movie 3", "Description 3", 3, "2023-03-03"));
        movies.add (new Movie("Movie 4", "Description 4", 4, "2023-04-04"));
        movies.add (new Movie("Movie 5", "Description 5", 5, "2023-05-05"));
        movies.add (new Movie("Movie 6", "Description 6", 6, "2023-06-06"));
        movies.add (new Movie("Movie 7", "Description 7", 7, "2023-07-07"));
        movies.add (new Movie("Movie 8", "Description 8", 8, "2023-08-08"));
        movies.add (new Movie("Movie 9", "Description 9", 9, "2023-09-09"));
        movies.add (new Movie("Movie 10", "Description 10", 10, "2023-10-10"));

        //Screening(Theatre theatre, Movie movie, String screenDate, String openDate , ArrayList<Seat> seatList)
        //Include multiple screenings for each movie

        ArrayList<Screening> screenings = new ArrayList<>();
        screenings.add (new Screening(theatres.get(0), movies.get(0), "2023-01-01", "2023-01-01", null));
        screenings.add (new Screening(theatres.get(1), movies.get(1), "2023-02-02", "2023-02-02", null));
        screenings.add (new Screening(theatres.get(2), movies.get(2), "2023-03-03", "2023-03-03", null));
        screenings.add (new Screening(theatres.get(0), movies.get(3), "2023-04-04", "2023-04-04", null));
        screenings.add (new Screening(theatres.get(1), movies.get(4), "2023-05-05", "2023-05-05", null));
        screenings.add (new Screening(theatres.get(2), movies.get(5), "2023-06-06", "2023-06-06", null));
        screenings.add (new Screening(theatres.get(0), movies.get(6), "2023-07-07", "2023-07-07", null));
        screenings.add (new Screening(theatres.get(1), movies.get(7), "2023-08-08", "2023-08-08", null));
        screenings.add (new Screening(theatres.get(2), movies.get(8), "2023-09-09", "2023-09-09", null));
        screenings.add (new Screening(theatres.get(0), movies.get(9), "2023-10-10", "2023-10-10", null));
        screenings.add (new Screening(theatres.get(0), movies.get(0), "2023-01-02", "2023-01-01", null));
        screenings.add (new Screening(theatres.get(1), movies.get(1), "2023-02-03", "2023-02-02", null));
        screenings.add (new Screening(theatres.get(2), movies.get(2), "2023-03-04", "2023-03-03", null));
        screenings.add (new Screening(theatres.get(0), movies.get(3), "2023-04-05", "2023-04-04", null));
        screenings.add (new Screening(theatres.get(1), movies.get(4), "2023-05-06", "2023-05-05", null));
        screenings.add (new Screening(theatres.get(2), movies.get(5), "2023-06-07", "2023-06-06", null));
        screenings.add (new Screening(theatres.get(0), movies.get(6), "2023-07-08", "2023-07-07", null));
        screenings.add (new Screening(theatres.get(1), movies.get(7), "2023-08-09", "2023-08-08", null));

        return screenings; // Placeholder for movies
    }

    @GetMapping("/fetchTheatres")
    public ArrayList<Theatre> getTheatres() {
        ArrayList<Theatre> theatres = new ArrayList<>();
        theatres.add (new Theatre("Theatre 1", "Description 1", 1));
        theatres.add (new Theatre("Theatre 2", "Description 2", 2));
        theatres.add (new Theatre("Theatre 3", "Description 3", 3));
        return theatres; // Placeholder for theatres
    }
    
    @RestController
    @RequestMapping("/signin")
    public class SignInController {
    
        @PostMapping
        public ResponseEntity<User> signIn(@RequestBody SignInRequest signInRequest) {
            String username = signInRequest.getUsername();
            String password = signInRequest.getPassword();
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
    
            // Placeholder for creating payment info
            PaymentInfo paymentInfo = new PaymentInfo("1234 5678 9012 2123", 123, "12/24");
            
            System.out.println("Payment info: " + paymentInfo);
            System.out.println("Payment info: " + paymentInfo.getCardNumber());
            System.out.println("Payment info: " + paymentInfo.getExpiryDate());
            System.out.println("Payment info: " + paymentInfo.getCvv());

            // Placeholder user
            RegisteredUser user = new RegisteredUser("John Doe", "U4k3i@example.com", "123 Main St", paymentInfo, password, username);
            
            System.out.println("User info: " + user);
            System.out.println("User info: " + user.getName());
            System.out.println("User info: " + user.getEmail());
            System.out.println("User info: " + user.getAddress());
            System.out.println("User info: " + user.getPaymentInfo());
            System.out.println("User info: " + user.getPassword());
            System.out.println("User info: " + user.getUsername());
            

            // Return the User object as JSON
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody RegisteredUser user) {
        System.out.println("User info: " + user);
        System.out.println("User info: " + user.getName());
        System.out.println("User info: " + user.getEmail());
        System.out.println("User info: " + user.getAddress());
        System.out.println("User info: " + user.getPaymentInfo());
        System.out.println("User info: " + user.getPassword());
        System.out.println("User info: " + user.getUsername());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/bookTicket")
    public ResponseEntity<TicketBooking> bookTicket(@RequestBody TicketBooking ticketBooking) {
        System.out.println("Ticket info: " + ticketBooking);
        return ResponseEntity.ok(ticketBooking);
    }

    @PostMapping("/purchase")
    public ResponseEntity<Receipt> purchase(@RequestBody Receipt receipt) {
        System.out.println("Receipt info: " + receipt);
        return ResponseEntity.ok(receipt);
    }
    
    public static class SignInRequest {
        private String username;
        private String password;
    
        // Getters and setters
    
        public String getUsername() {
            return username;
        }
    
        public void setUsername(String username) {
            this.username = username;
        }
    
        public String getPassword() {
            return password;
        }
    
        public void setPassword(String password) {
            this.password = password;
        }
    }
}
