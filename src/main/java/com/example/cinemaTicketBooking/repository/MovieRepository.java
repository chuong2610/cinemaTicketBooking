package com.example.cinemaTicketBooking.repository;

import com.example.cinemaTicketBooking.entity.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByDateBefore(LocalDate date,Pageable pageable);
    List<Movie> findByDateAfter(LocalDate date,Pageable pageable);

    @Query("SELECT m FROM Movie m " +
            "JOIN Review r ON m.id = r.movie.id " +
            "GROUP BY m.id " +
            "ORDER BY COUNT(r.id) DESC")
    List<Movie> findTopMoviesByReviewCount(Pageable pageable);

    List<Movie> findByTicketOrdersSeatRoomLocationIdAndSchedulesDateBetween(int id, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    List<Movie> findByTicketOrdersSeatRoomLocationId(int id ,Pageable pageable);
}
