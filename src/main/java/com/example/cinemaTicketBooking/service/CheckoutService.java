package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.payload.request.CheckoutRequest;
import com.example.cinemaTicketBooking.payload.request.SeatReleaseRequest;
import com.example.cinemaTicketBooking.payload.response.CheckoutRessponse;

public interface CheckoutService {
    CheckoutRessponse checkoutInformation(CheckoutRequest request);
}
