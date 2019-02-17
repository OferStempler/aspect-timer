package ofer.stempler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class TimerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimerApplication.class, args);
	}

}

