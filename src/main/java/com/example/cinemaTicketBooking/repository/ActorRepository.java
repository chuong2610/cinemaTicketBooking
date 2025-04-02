package com.example.cinemaTicketBooking.repository;

import com.example.cinemaTicketBooking.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
