package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.dto.SeatDTO;
import com.example.cinemaTicketBooking.payload.request.CheckoutRequest;
import com.example.cinemaTicketBooking.payload.request.SeatReleaseRequest;
import com.example.cinemaTicketBooking.payload.response.CheckoutRessponse;
import com.example.cinemaTicketBooking.repository.MovieRepository;
import com.example.cinemaTicketBooking.service.CheckoutService;
import com.example.cinemaTicketBooking.service.MovieService;
import com.example.cinemaTicketBooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CheckoutServiceImp implements CheckoutService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private SeatService seatService;

    @Override
    public CheckoutRessponse checkoutInformation(CheckoutRequest request) {
        long totalPrice = 0;
        List<String> seats = new ArrayList<>();
        CheckoutRessponse checkoutRessponse = new CheckoutRessponse();
        checkoutRessponse.setMovie(movieRepository.findById(request.getMovieId()).get().getTitle());
        checkoutRessponse.setSchedule(request.getSchedule());
        checkoutRessponse.setLocation(request.getLocation());

        Set<String> keys=seatService.getSeatsByUserIdAndMovieSchedule(request.getUserId(), request.getMovieId(), request.getSchedule());
        for(SeatDTO seatDTO: seatService.getSeatDTOFromKey(keys)){
            totalPrice += seatDTO.getPrice();
            seats.add(seatDTO.getRow()+seatDTO.getNumber());
        }
        checkoutRessponse.setTotalPrice(totalPrice);
        checkoutRessponse.setSeats(seats);
        checkoutRessponse.setAddress(request.getAddress());

        return checkoutRessponse;
    }
}
