package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.dto.*;
import com.example.cinemaTicketBooking.entity.Image;
import com.example.cinemaTicketBooking.entity.Movie;
import com.example.cinemaTicketBooking.entity.Review;
import com.example.cinemaTicketBooking.entity.TicketOrder;
import com.example.cinemaTicketBooking.repository.MovieRepository;
import com.example.cinemaTicketBooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class MovieServiceImp implements MovieService {
    @Autowired
    private MovieRepository movieRepository;



    @Override
    public List<MovieDTO> findMovieNowShowing(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<MovieDTO> movieDTOs = movieRepository.findNowShowingMovies(pageable).stream().map(movie -> {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setId(movie.getId());
            movieDTO.setTitle(movie.getTitle());
            movieDTO.setDate(movie.getDate());
            movieDTO.setDuration(movie.getDuration());
            movieDTO.setDescription(movie.getDescription());
            List< ImageDTO> imageDTOs = new ArrayList<>();
            for(Image image: movie.getImages()){
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setId(image.getId());
                imageDTO.setImg("http://localhost:8080/file/"+image.getImg());
                imageDTOs.add(imageDTO);
            }
            movieDTO.setImageDTOs(imageDTOs);

            List<ReviewDTO> reviewDTOs = new ArrayList<>();
            int total=0;
            for (Review review : movie.getReviews()) {
                total+=review.getRating();
                ReviewDTO reviewDTO = new ReviewDTO();
                reviewDTO.setId(review.getId());
                reviewDTO.setRating(review.getRating());
                reviewDTO.setDescription(review.getDescription());
                reviewDTOs.add(reviewDTO);
            }
            movieDTO.setAvrRating(total/movie.getReviews().size());
            movieDTO.setReviewDTOs(reviewDTOs);

            List<TicketOrderDTO> ticketOrderDTOs = new ArrayList<>();
            for(TicketOrder ticketOrder: movie.getTicketOrders()){
                TicketOrderDTO ticketOrderDTO = new TicketOrderDTO();
                ticketOrderDTO.setId(ticketOrder.getId());
                ticketOrderDTOs.add(ticketOrderDTO);
            }
            movieDTO.setTicketOrderDTOs(ticketOrderDTOs);

            List<String> genres = movie.getMovieGenres().stream().map(movieGenre ->
                    movieGenre.getGenre().getName()
            ).toList();
            movieDTO.setGenres(genres);

            List<ActorDTO> actorDTOs = movie.getMovieActors().stream().map(movieActor -> {
                ActorDTO actorDTO = new ActorDTO();
                actorDTO.setId(movieActor.getActor().getId());
                actorDTO.setName(movieActor.getActor().getName());
                return actorDTO;
            }).toList();
            movieDTO.setActorDTOs(actorDTOs);

            return movieDTO;
        }).toList();

        return movieDTOs;
    }

    @Override
    public List<MovieDTO> findMovieUpComming(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<MovieDTO> movieDTOs = movieRepository.findByDateAfter(LocalDate.now(),pageable).stream().map(movie -> {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setId(movie.getId());
            movieDTO.setTitle(movie.getTitle());
            movieDTO.setDate(movie.getDate());
            movieDTO.setDuration(movie.getDuration());
            movieDTO.setDescription(movie.getDescription());
            List< ImageDTO> imageDTOs = new ArrayList<>();
            for(Image image: movie.getImages()){
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setId(image.getId());
                imageDTO.setImg("http://localhost:8080/file/"+image.getImg());
                imageDTOs.add(imageDTO);
            }
            movieDTO.setImageDTOs(imageDTOs);

            List<ReviewDTO> reviewDTOs = new ArrayList<>();
            int total=0;
            for (Review review : movie.getReviews()) {
                total+=review.getRating();
                ReviewDTO reviewDTO = new ReviewDTO();
                reviewDTO.setId(review.getId());
                reviewDTO.setRating(review.getRating());
                reviewDTO.setDescription(review.getDescription());
                reviewDTOs.add(reviewDTO);
            }
            movieDTO.setAvrRating(total/movie.getReviews().size());
            movieDTO.setReviewDTOs(reviewDTOs);

            List<TicketOrderDTO> ticketOrderDTOs = new ArrayList<>();
            for(TicketOrder ticketOrder: movie.getTicketOrders()){
                TicketOrderDTO ticketOrderDTO = new TicketOrderDTO();
                ticketOrderDTO.setId(ticketOrder.getId());
                ticketOrderDTOs.add(ticketOrderDTO);
            }
            movieDTO.setTicketOrderDTOs(ticketOrderDTOs);

            List<String> genres = movie.getMovieGenres().stream().map(movieGenre ->
                    movieGenre.getGenre().getName()
            ).toList();
            movieDTO.setGenres(genres);

            List<ActorDTO> actorDTOs = movie.getMovieActors().stream().map(movieActor -> {
                ActorDTO actorDTO = new ActorDTO();
                actorDTO.setId(movieActor.getActor().getId());
                actorDTO.setName(movieActor.getActor().getName());
                return actorDTO;
            }).toList();
            movieDTO.setActorDTOs(actorDTOs);

            return movieDTO;
        }).toList();

        return movieDTOs;


    }

    @Override
    public List<MovieDTO> findTopMoviesByReviewCount(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<MovieDTO> movieDTOs = movieRepository.findTopMoviesByReviewCount(pageable).stream().map(movie -> {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setId(movie.getId());
            movieDTO.setTitle(movie.getTitle());
            movieDTO.setDate(movie.getDate());
            movieDTO.setDuration(movie.getDuration());
            movieDTO.setDescription(movie.getDescription());
            List< ImageDTO> imageDTOs = new ArrayList<>();
            for(Image image: movie.getImages()){
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setId(image.getId());
                imageDTO.setImg("http://localhost:8080/file/"+image.getImg());
                imageDTOs.add(imageDTO);
            }
            movieDTO.setImageDTOs(imageDTOs);

            List<ReviewDTO> reviewDTOs = new ArrayList<>();
            int total=0;
            for (Review review : movie.getReviews()) {
                total+=review.getRating();
                ReviewDTO reviewDTO = new ReviewDTO();
                reviewDTO.setId(review.getId());
                reviewDTO.setRating(review.getRating());
                reviewDTO.setDescription(review.getDescription());
                reviewDTOs.add(reviewDTO);
            }
            movieDTO.setAvrRating(total/movie.getReviews().size());
            movieDTO.setReviewDTOs(reviewDTOs);

            List<TicketOrderDTO> ticketOrderDTOs = new ArrayList<>();
            for(TicketOrder ticketOrder: movie.getTicketOrders()){
                TicketOrderDTO ticketOrderDTO = new TicketOrderDTO();
                ticketOrderDTO.setId(ticketOrder.getId());
                ticketOrderDTOs.add(ticketOrderDTO);
            }
            movieDTO.setTicketOrderDTOs(ticketOrderDTOs);

            List<String> genres = movie.getMovieGenres().stream().map(movieGenre ->
                movieGenre.getGenre().getName()
            ).toList();
            movieDTO.setGenres(genres);

            List<ActorDTO> actorDTOs = movie.getMovieActors().stream().map(movieActor -> {
                ActorDTO actorDTO = new ActorDTO();
                actorDTO.setId(movieActor.getActor().getId());
                actorDTO.setName(movieActor.getActor().getName());
                return actorDTO;
            }).toList();
            movieDTO.setActorDTOs(actorDTOs);

            return movieDTO;
        }).toList();

        return movieDTOs;


    }

    @Override
    public MovieDTO findById(int id) {
        Movie movie = movieRepository.findById(id).get();
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDate(movie.getDate());
        movieDTO.setDuration(movie.getDuration());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setVideo(movie.getVideo());
        List< ImageDTO> imageDTOs = new ArrayList<>();
        for(Image image: movie.getImages()){
            ImageDTO imageDTO = new ImageDTO();
            imageDTO.setId(image.getId());
            imageDTO.setImg("http://localhost:8080/file/"+image.getImg());
            imageDTOs.add(imageDTO);
        }
        movieDTO.setImageDTOs(imageDTOs);
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        int total=0;
        for (Review review : movie.getReviews()) {
            total+=review.getRating();
            ReviewDTO reviewDTO = new ReviewDTO();
            UserDTO userDTO = new UserDTO();
            userDTO.setName(review.getCustomer().getName());
            userDTO.setId(review.getCustomer().getId());
            userDTO.setEmail(review.getCustomer().getEmail());
            userDTO.setImg("http://localhost:8080/file/"+review.getCustomer().getImg());
            userDTO.setPhone(review.getCustomer().getPhone());
            reviewDTO.setUserDTO(userDTO);
            reviewDTO.setId(review.getId());
            reviewDTO.setRating(review.getRating());
            reviewDTO.setDescription(review.getDescription());
            reviewDTOs.add(reviewDTO);
        }
        movieDTO.setAvrRating(total/movie.getReviews().size());
        movieDTO.setReviewDTOs(reviewDTOs);

        List<TicketOrderDTO> ticketOrderDTOs = new ArrayList<>();
        for(TicketOrder ticketOrder: movie.getTicketOrders()){
            TicketOrderDTO ticketOrderDTO = new TicketOrderDTO();
            ticketOrderDTO.setId(ticketOrder.getId());
            ticketOrderDTOs.add(ticketOrderDTO);
        }
        movieDTO.setTicketOrderDTOs(ticketOrderDTOs);
        List<String> genres = movie.getMovieGenres().stream().map(movieGenre ->
                movieGenre.getGenre().getName()
        ).toList();
        movieDTO.setGenres(genres);

        List<ActorDTO> actorDTOs = movie.getMovieActors().stream().map(movieActor -> {
            ActorDTO actorDTO = new ActorDTO();
            actorDTO.setId(movieActor.getActor().getId());
            actorDTO.setName(movieActor.getActor().getName());
            actorDTO.setBirthday(movieActor.getActor().getBirthday());
            actorDTO.setImg("http://localhost:8080/file/"+movieActor.getActor().getImg());
            return actorDTO;
        }).toList();
        movieDTO.setActorDTOs(actorDTOs);




        return movieDTO;
    }

    @Override
    public List<MovieDTO> findByLocationIdAndScheduleDate(int id, LocalDateTime startDate,LocalDateTime endDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<MovieDTO> movieDTOs = movieRepository.findByTicketOrdersSeatRoomLocationIdAndSchedulesDateBetween(id,startDate,endDate,pageable).stream().map(movie -> {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setId(movie.getId());
            movieDTO.setTitle(movie.getTitle());
            movieDTO.setDate(movie.getDate());
            movieDTO.setDuration(movie.getDuration());
            movieDTO.setDescription(movie.getDescription());
            List< ImageDTO> imageDTOs = new ArrayList<>();
            for(Image image: movie.getImages()){
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setId(image.getId());
                imageDTO.setImg("http://localhost:8080/file/"+image.getImg());
                imageDTOs.add(imageDTO);
            }
            movieDTO.setImageDTOs(imageDTOs);

            List<ReviewDTO> reviewDTOs = new ArrayList<>();
            int total=0;
            for (Review review : movie.getReviews()) {
                total+=review.getRating();
                ReviewDTO reviewDTO = new ReviewDTO();
                reviewDTO.setId(review.getId());
                reviewDTO.setRating(review.getRating());
                reviewDTO.setDescription(review.getDescription());
                reviewDTOs.add(reviewDTO);
            }
            movieDTO.setAvrRating(total/movie.getReviews().size());
            movieDTO.setReviewDTOs(reviewDTOs);

            List<TicketOrderDTO> ticketOrderDTOs = new ArrayList<>();
            for(TicketOrder ticketOrder: movie.getTicketOrders()){
                TicketOrderDTO ticketOrderDTO = new TicketOrderDTO();
                ticketOrderDTO.setId(ticketOrder.getId());
                ticketOrderDTOs.add(ticketOrderDTO);
            }
            movieDTO.setTicketOrderDTOs(ticketOrderDTOs);

            List<String> genres = movie.getMovieGenres().stream().map(movieGenre ->
                    movieGenre.getGenre().getName()
            ).toList();
            movieDTO.setGenres(genres);

            List<ActorDTO> actorDTOs = movie.getMovieActors().stream().map(movieActor -> {
                ActorDTO actorDTO = new ActorDTO();
                actorDTO.setId(movieActor.getActor().getId());
                actorDTO.setName(movieActor.getActor().getName());
                return actorDTO;
            }).toList();
            movieDTO.setActorDTOs(actorDTOs);

            return movieDTO;
        }).toList();

        return movieDTOs;


    }
    @Override
    public List<MovieDTO> findByLocationId(int id,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<MovieDTO> movieDTOs = movieRepository.findByTicketOrdersSeatRoomLocationId(id,pageable).stream().map(movie -> {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setId(movie.getId());
            movieDTO.setTitle(movie.getTitle());
            movieDTO.setDate(movie.getDate());
            movieDTO.setDuration(movie.getDuration());
            movieDTO.setDescription(movie.getDescription());
            List< ImageDTO> imageDTOs = new ArrayList<>();
            for(Image image: movie.getImages()){
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setId(image.getId());
                imageDTO.setImg("http://localhost:8080/file/"+image.getImg());
                imageDTOs.add(imageDTO);
            }
            movieDTO.setImageDTOs(imageDTOs);

            List<ReviewDTO> reviewDTOs = new ArrayList<>();
            int total=0;
            for (Review review : movie.getReviews()) {
                total+=review.getRating();
                ReviewDTO reviewDTO = new ReviewDTO();
                reviewDTO.setId(review.getId());
                reviewDTO.setRating(review.getRating());
                reviewDTO.setDescription(review.getDescription());
                reviewDTOs.add(reviewDTO);
            }
            movieDTO.setAvrRating(total/movie.getReviews().size());
            movieDTO.setReviewDTOs(reviewDTOs);

            List<TicketOrderDTO> ticketOrderDTOs = new ArrayList<>();
            for(TicketOrder ticketOrder: movie.getTicketOrders()){
                TicketOrderDTO ticketOrderDTO = new TicketOrderDTO();
                ticketOrderDTO.setId(ticketOrder.getId());
                ticketOrderDTOs.add(ticketOrderDTO);
            }
            movieDTO.setTicketOrderDTOs(ticketOrderDTOs);

            List<String> genres = movie.getMovieGenres().stream().map(movieGenre ->
                    movieGenre.getGenre().getName()
            ).toList();
            movieDTO.setGenres(genres);

            List<ActorDTO> actorDTOs = movie.getMovieActors().stream().map(movieActor -> {
                ActorDTO actorDTO = new ActorDTO();
                actorDTO.setId(movieActor.getActor().getId());
                actorDTO.setName(movieActor.getActor().getName());
                return actorDTO;
            }).toList();
            movieDTO.setActorDTOs(actorDTOs);

            return movieDTO;
        }).toList();

        return movieDTOs;


    }
}
