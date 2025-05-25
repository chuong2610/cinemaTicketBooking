package com.example.cinemaTicketBooking.repository;

import com.example.cinemaTicketBooking.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findByStatusAndExpiredAtBefore(String status, LocalDateTime expiredAtBefore);
}
