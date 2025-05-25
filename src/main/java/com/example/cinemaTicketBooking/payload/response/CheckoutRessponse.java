package com.example.cinemaTicketBooking.payload.response;

import com.example.cinemaTicketBooking.dto.SeatDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Data
public class CheckoutRessponse {
    private String movie;
    private String location;
    private String address;
    private LocalDateTime schedule;
    private List<String> seats;
    private long totalPrice;

}
