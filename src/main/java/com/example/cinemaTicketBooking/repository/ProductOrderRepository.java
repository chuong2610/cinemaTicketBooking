package com.example.cinemaTicketBooking.repository;

import com.example.cinemaTicketBooking.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {
    List<ProductOrder> findByBillId(int movieId);
}
