package com.example.cinemaTicketBooking.repository;

import com.example.cinemaTicketBooking.entity.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByDateBefore(LocalDate date);
    List<Movie> findByDateAfter(LocalDate date);

    @Query("SELECT m FROM Movie m " +
            "JOIN Review r ON m.id = r.movie.id " +
            "GROUP BY m.id " +
            "ORDER BY COUNT(r.id) DESC")
    List<Movie> findTopMoviesByReviewCount(Pageable pageable);
}
