package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.dto.LocationDTO;

import java.util.List;

public interface LocationService {
    List<LocationDTO> findByCityId(int id);
    List<LocationDTO> findAll();
}
