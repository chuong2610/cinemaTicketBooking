package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.dto.LocationDTO;

import com.example.cinemaTicketBooking.repository.LocationRepository;
import com.example.cinemaTicketBooking.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImp implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<LocationDTO> findByCityId(int id) {

        List<LocationDTO> locationDTOs = locationRepository.findByCityId(id).stream().map(location -> {
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setId(location.getId());
            locationDTO.setName(location.getName());
            locationDTO.setAddress(location.getAddress());
            return locationDTO;

        }).toList();
        return locationDTOs;
    }

    @Override
    public List<LocationDTO> findAll() {

        List<LocationDTO> locationDTOs = locationRepository.findAll().stream().map(location -> {
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setId(location.getId());
            locationDTO.setName(location.getName());
            locationDTO.setAddress(location.getAddress());
            return locationDTO;

        }).toList();
        return locationDTOs;
    }
}
