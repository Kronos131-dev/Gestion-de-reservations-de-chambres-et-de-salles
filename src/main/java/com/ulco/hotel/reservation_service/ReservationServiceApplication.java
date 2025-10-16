package com.ulco.hotel.reservation_service;

import com.ulco.hotel.reservation_service.persistence.Reservation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
		//new Reservation().setIdReservation(4L);
	}
}
