package ru.vereshchakov.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.vereshchakov.booking.config.ApplicationConfig;

@SpringBootApplication
@ComponentScan("ru.vereshchakov")
@Import(ApplicationConfig.class)
public class BookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}
}
