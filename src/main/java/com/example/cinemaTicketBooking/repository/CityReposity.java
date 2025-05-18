package com.example.cinemaTicketBooking.repository;

import com.example.cinemaTicketBooking.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityReposity extends JpaRepository<City, Integer> {
}
