package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.dto.LocationDTO;
import com.example.cinemaTicketBooking.entity.Location;
import com.example.cinemaTicketBooking.repository.LocationRepository;
import com.example.cinemaTicketBooking.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImp implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<LocationDTO> findByCityId(int id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<LocationDTO> locationDTOs = locationRepository.findByCityId(id,pageable).stream().map(location -> {
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setId(location.getId());
            locationDTO.setName(location.getName());
            locationDTO.setAddress(location.getAddress());
            return locationDTO;

        }).toList();
        return locationDTOs;
    }

    @Override
    public List<LocationDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<LocationDTO> locationDTOs = locationRepository.findAll(pageable).stream().map(location -> {
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setId(location.getId());
            locationDTO.setName(location.getName());
            locationDTO.setAddress(location.getAddress());
            return locationDTO;

        }).toList();
        return locationDTOs;
    }
}
