package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.dto.ImageDTO;
import com.example.cinemaTicketBooking.dto.MovieDTO;
import com.example.cinemaTicketBooking.dto.ReviewDTO;
import com.example.cinemaTicketBooking.dto.TicketOrderDTO;
import com.example.cinemaTicketBooking.entity.Image;
import com.example.cinemaTicketBooking.entity.Review;
import com.example.cinemaTicketBooking.entity.TicketOrder;
import com.example.cinemaTicketBooking.repository.MovieRepository;
import com.example.cinemaTicketBooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class MovieServiceImp implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<MovieDTO> findByDateBefore(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<MovieDTO> movieDTOs = movieRepository.findByDateBefore(LocalDate.now(),pageable).stream().map(movie -> {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setId(movie.getId());
            movieDTO.setTitle(movie.getTitle());
            movieDTO.setDate(movie.getDate());
            movieDTO.setDescription(movie.getDescription());
            List< ImageDTO> imageDTOs = new ArrayList<>();
            for(Image image: movie.getImages()){
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setId(image.getId());
                imageDTO.setImg("localhot:8080/file/"+image.getImg());
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

            return movieDTO;
        }).toList();

        return movieDTOs;
    }

    @Override
    public List<MovieDTO> findByDateAfter(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<MovieDTO> movieDTOs = movieRepository.findByDateAfter(LocalDate.now(),pageable).stream().map(movie -> {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setId(movie.getId());
            movieDTO.setTitle(movie.getTitle());
            movieDTO.setDate(movie.getDate());
            movieDTO.setDescription(movie.getDescription());
            List< ImageDTO> imageDTOs = new ArrayList<>();
            for(Image image: movie.getImages()){
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setId(image.getId());
                imageDTO.setImg("localhot:8080/file/"+image.getImg());
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
            movieDTO.setDescription(movie.getDescription());
            List< ImageDTO> imageDTOs = new ArrayList<>();
            for(Image image: movie.getImages()){
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setId(image.getId());
                imageDTO.setImg("localhot:8080/file/"+image.getImg());
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

            return movieDTO;
        }).toList();

        return movieDTOs;


    }
}
