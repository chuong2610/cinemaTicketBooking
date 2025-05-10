package com.example.cinemaTicketBooking.repository;

import com.example.cinemaTicketBooking.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByType(String type);
}
