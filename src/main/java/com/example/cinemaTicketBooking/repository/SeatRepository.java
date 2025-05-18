package com.example.cinemaTicketBooking.repository;

import com.example.cinemaTicketBooking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    @Query("SELECT s FROM Seat s " +
            "JOIN s.room r " +
            "JOIN r.schedules sch " +
            "WHERE s.status = 1 " +
            "AND sch.movie.id = :movieId " +
            "AND sch.date = :date")
    List<Seat> findSeatsByMovieIdAndDate(@Param("movieId") int movieId,
                                         @Param("date") LocalDateTime date);

    @Query("SELECT s FROM Seat s " +
            "JOIN s.room r " +
            "JOIN r.schedules sch " +
            "WHERE sch.movie.id = :movieId " +
            "AND sch.date = :date " +
            "AND s.row = :row " +
            "AND s.number = :number")
    Seat findSeatByMovieIdAndScheduleDateAndRowAndNumber(@Param("movieId") int movieId,
                                                         @Param("date") LocalDateTime date,
                                                         @Param("row") String row,
                                                         @Param("number") int number);

}
