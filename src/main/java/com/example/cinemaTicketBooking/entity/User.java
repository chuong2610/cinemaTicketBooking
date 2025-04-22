package com.example.cinemaTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String phone;

    private String email;

    private String password;

    private String img;

    @ManyToOne
@JoinColumn(name = "locationId")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;



    @OneToOne(mappedBy = "customer")
    private Card card;

    @OneToMany(mappedBy = "customer")
    private List<Review> reviews;

    @OneToMany(mappedBy = "customer")
    private List<Bill> billCustomer;

    @OneToMany(mappedBy = "employee")
    private List<Bill> billEmployee;
}
