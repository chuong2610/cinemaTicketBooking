package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.dto.ScheduleDTO;
import com.example.cinemaTicketBooking.entity.Schedule;
import com.example.cinemaTicketBooking.repository.ScheduleRepository;
import com.example.cinemaTicketBooking.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImp implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Override
    public List<ScheduleDTO> findByRoomLocationIdAndMovieId(int locationId, Integer movieId) {
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        for (Schedule schedule : scheduleRepository.findByRoomLocationIdAndMovieId(locationId, movieId)) {
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTO.setId(schedule.getId());
            scheduleDTO.setDate(schedule.getDate());
            scheduleDTOs.add(scheduleDTO);
        }
        return scheduleDTOs;
    }
}
