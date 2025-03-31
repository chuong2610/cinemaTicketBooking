package com.example.cinemaTicketBooking.entity;

import com.example.cinemaTicketBooking.entity.key.MovieGenreId;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity(name = "MovieGenre")
public class MovieGenre {
    @EmbeddedId
    private MovieGenreId movieGenreId;

    @ManyToOne
    @JoinColumn(name="genreId", insertable = false, updatable = false)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "movieId", insertable = false, updatable = false)
    private Movie movie;


}
