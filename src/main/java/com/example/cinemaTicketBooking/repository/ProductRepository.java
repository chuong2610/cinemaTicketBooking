package com.example.cinemaTicketBooking.repository;

import com.example.cinemaTicketBooking.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
