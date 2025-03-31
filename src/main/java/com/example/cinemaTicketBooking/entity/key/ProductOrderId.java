package com.example.cinemaTicketBooking.entity.key;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ProductOrderId {
    private int billId;
    private int productId;

}
