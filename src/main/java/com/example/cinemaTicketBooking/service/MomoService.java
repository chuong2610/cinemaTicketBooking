package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.dto.CheckoutDTO;
import com.example.cinemaTicketBooking.payload.request.CheckoutRequest;
import com.example.cinemaTicketBooking.payload.request.MomoRequest;
import com.example.cinemaTicketBooking.payload.response.CheckoutRessponse;
import com.example.cinemaTicketBooking.payload.response.MomoResponse;

public interface MomoService {
    MomoRequest getMomoRequest(CheckoutDTO checkoutDTO, int billId);
    void processMomoIpn(MomoResponse response);
}
