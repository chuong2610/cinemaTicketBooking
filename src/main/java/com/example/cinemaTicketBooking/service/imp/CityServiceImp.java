package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.dto.CityDTO;
import com.example.cinemaTicketBooking.entity.City;
import com.example.cinemaTicketBooking.repository.CityReposity;
import com.example.cinemaTicketBooking.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImp implements CityService {
    @Autowired
    private CityReposity cityReposity;
    @Override
    public List<CityDTO> findCities(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<CityDTO> cities = cityReposity.findAll(pageable).stream().map(city -> {
            CityDTO cityDTO = new CityDTO();
            cityDTO.setId(city.getId());
            cityDTO.setName(city.getName());
            return cityDTO;

        }).toList();
        return cities;
    }
}
