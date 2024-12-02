package Group15._Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.setProperty("server.port", "8083"); // Port 8083
		SpringApplication.run(Application.class, args);
	}

}
