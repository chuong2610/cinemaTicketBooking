package com.example.cinemaTicketBooking.repository;

import com.example.cinemaTicketBooking.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByType(String type);
    Product findByName(String name);
}
