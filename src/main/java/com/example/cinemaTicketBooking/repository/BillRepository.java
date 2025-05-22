package com.example.cinemaTicketBooking.repository;

import com.example.cinemaTicketBooking.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
}
