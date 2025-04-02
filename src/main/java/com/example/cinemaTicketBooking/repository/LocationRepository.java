package com.example.cinemaTicketBooking.repository;

import com.example.cinemaTicketBooking.entity.Location;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findByCityId(int id, Pageable pageable);
}
