package Group15._Project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot!";
    }

    @GetMapping("/test")
    public String test(@RequestParam(required = false) String[] args) {
        if (args != null && args.length > 0) {
            System.out.println("Hello from Java in TestProgram!");
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
    
}
