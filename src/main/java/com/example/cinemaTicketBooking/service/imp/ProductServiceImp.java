package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.dto.ProductDTO;
import com.example.cinemaTicketBooking.entity.Product;
import com.example.cinemaTicketBooking.repository.ProductRepository;
import com.example.cinemaTicketBooking.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productDTOs =new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            productDTOs.add(mapToDTO(product));
        }
        return productDTOs;
    }

    public ProductDTO mapToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setImg("http://localhost:8080/file/"+product.getImg());
        return productDTO;
    }
}
