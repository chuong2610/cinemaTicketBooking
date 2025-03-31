package com.example.cinemaTicketBooking.entity;

import com.example.cinemaTicketBooking.entity.key.ProductOrderId;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "ProductOreder")
public class ProductOrder {
    @EmbeddedId
    private ProductOrderId productOrderId;

    private int number;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "billId", insertable = false, updatable = false)
    private Bill bill;


}
