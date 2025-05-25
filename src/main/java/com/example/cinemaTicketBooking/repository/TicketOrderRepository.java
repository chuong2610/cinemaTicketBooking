package com.example.cinemaTicketBooking.repository;

import com.example.cinemaTicketBooking.entity.Seat;
import com.example.cinemaTicketBooking.entity.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TicketOrderRepository extends JpaRepository<TicketOrder, Integer> {
    List<TicketOrder> findByBillId(int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM TicketOrder t WHERE t.bill.id = :billId")
    void deleteByBillId(@Param("billId") int billId);
}
