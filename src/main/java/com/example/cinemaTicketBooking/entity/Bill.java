package com.example.cinemaTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "Bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private User employee;

    @OneToMany(mappedBy = "bill")
    private List<TicketOrder> tickerOrders;

    @OneToMany(mappedBy = "bill")
    private List<ProductOrder> productOrders;


}
