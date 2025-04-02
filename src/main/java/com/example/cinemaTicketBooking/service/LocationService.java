package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.dto.LocationDTO;

import java.util.List;

public interface LocationService {
    List<LocationDTO> findByCityId(int id, int page, int size);
    List<LocationDTO> findAll(int page, int size);
}
