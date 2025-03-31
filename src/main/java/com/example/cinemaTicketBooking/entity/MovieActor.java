package com.example.cinemaTicketBooking.entity;

import com.example.cinemaTicketBooking.entity.key.MovieActorId;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "MovieActor")
@Data
public class MovieActor {
    @EmbeddedId
    private MovieActorId movieActorId;

    @ManyToOne
    @JoinColumn(name = "actorId", insertable = false, updatable = false)
    private Actor actor;

    @ManyToOne
    @JoinColumn(name = "movieId", insertable = false, updatable = false)
    private Movie movie;

}
