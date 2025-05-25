package com.example.cinemaTicketBooking.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Data
public class CheckoutDTO {
    private int userId;
    private int movieId;
    private String location;
    private String address;
    private LocalDateTime schedule;
    private List<String> seats;
    private Map<String,Integer> products;
    private long totalPrice;

    public String getOrderInfoForMomo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedSchedule = schedule.format(formatter);
        String seatList = String.join(", ", seats);

        return "Thanh toán vé: " + movieId + " | Rạp: " + location + " | Ghế: " + seatList + " | Suất: " + formattedSchedule;
    }
}
