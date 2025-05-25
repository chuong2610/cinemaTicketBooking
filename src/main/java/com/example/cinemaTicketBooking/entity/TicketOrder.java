package com.example.cinemaTicketBooking.entity;

import com.example.cinemaTicketBooking.dto.ScheduleDTO;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity(name = "TicketOrder")
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "scheduleId")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "billId")
    private Bill bill;

    @OneToOne
    @JoinColumn(name = "seatId")
    private Seat seat;
}
