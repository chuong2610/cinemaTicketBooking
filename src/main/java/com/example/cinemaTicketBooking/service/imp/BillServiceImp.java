package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.dto.CheckoutDTO;
import com.example.cinemaTicketBooking.entity.*;
import com.example.cinemaTicketBooking.repository.*;
import com.example.cinemaTicketBooking.service.BillService;
import com.example.cinemaTicketBooking.service.SeatService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BillServiceImp implements BillService {
    private BillRepository billRepository;
    private SeatRepository seatRepository;
    private TicketOrderRepository ticketOrderRepository;
    private ProductRepository productRepository;
    private ProductOrderRepository productOrderRepository;
    @Override
    public String createBill(CheckoutDTO checkoutDTO) {
        User user = new User();
        user.setId(checkoutDTO.getUserId());
        Bill bill = new Bill();
        bill.setCreateTime(LocalDateTime.now());
        bill.setTotalAmount(checkoutDTO.getTotalPrice());
        bill.setCustomer(user);
        bill.setStatus("Pending");
        Bill billSaved=billRepository.save(bill);
        Movie movie = new Movie();
        movie.setId(checkoutDTO.getMovieId());
        for(String seat: checkoutDTO.getSeats()){
            String row = seat.replaceAll("[0-9]", "");   // => "A"
            String column = seat.replaceAll("[^0-9]", ""); // => "5"
            int columnNumber = Integer.parseInt(column); // => 5
            TicketOrder ticketOrder = new TicketOrder();
            ticketOrder.setSeat(seatRepository.findSeatByMovieIdAndScheduleDateAndRowAndNumber(checkoutDTO.getMovieId(),checkoutDTO.getSchedule(),row,columnNumber));
            ticketOrder.setBill(billSaved);
            ticketOrder.setMovie(movie);
            ticketOrderRepository.save(ticketOrder);
        }
        for(Map.Entry<String,Integer> entry : checkoutDTO.getProducts().entrySet()){
            ProductOrder productOrder = new ProductOrder();
            Product product = productRepository.findByName(entry.getKey());
            productOrder.setBill(billSaved);
            productOrder.setProduct(product);
            productOrder.setNumber(entry.getValue());
            productOrderRepository.save(productOrder);
        }




        return "";
    }
}
