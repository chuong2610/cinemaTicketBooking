package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.dto.CheckoutDTO;
import com.example.cinemaTicketBooking.entity.Bill;

public interface BillService {
    String createBill(CheckoutDTO checkoutDTO);
}
