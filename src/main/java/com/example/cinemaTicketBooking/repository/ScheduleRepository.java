package com.example.cinemaTicketBooking.repository;

import com.example.cinemaTicketBooking.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByRoomLocationIdAndMovieId(int locationId, Integer movieId);
}
