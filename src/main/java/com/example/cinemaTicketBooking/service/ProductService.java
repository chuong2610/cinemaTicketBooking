package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    List<ProductDTO> findByType(String type);
}
