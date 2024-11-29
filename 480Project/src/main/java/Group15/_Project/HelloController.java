package Group15._Project;

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
