package com.example.cinemaTicketBooking.repository;

import com.example.cinemaTicketBooking.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query("SELECT s FROM Schedule s " +
            "WHERE s.room.location.id = :locationId " +
            "AND s.movie.id = :movieId " +
            "AND s.date >= :startOfDay AND s.date < :startOfNextDay")
    List<Schedule> findByRoomLocationIdAndMovieIdAndDate(
            @Param("locationId") int locationId,
            @Param("movieId") Integer movieId,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("startOfNextDay") LocalDateTime startOfNextDay);
}
