package com.example.cinemaTicketBooking.repository;

import com.example.cinemaTicketBooking.entity.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketOrderRepository extends JpaRepository<TicketOrder, Integer> {
}
