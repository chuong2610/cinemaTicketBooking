package com.example.cinemaTicketBooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CinemaTicketBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaTicketBookingApplication.class, args);
	}

}
