package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.dto.CityDTO;
import com.example.cinemaTicketBooking.entity.City;

import java.util.List;

public interface CityService {
    List<CityDTO> findCities();
}
