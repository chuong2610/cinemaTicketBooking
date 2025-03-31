package com.example.cinemaTicketBooking.entity.key;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class MovieActorId implements Serializable {
    private int movieId;
    private int actorId;

}
