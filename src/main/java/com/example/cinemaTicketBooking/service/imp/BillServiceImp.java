package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.dto.CheckoutDTO;
import com.example.cinemaTicketBooking.entity.*;
import com.example.cinemaTicketBooking.repository.*;
import com.example.cinemaTicketBooking.service.BillService;
import com.example.cinemaTicketBooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BillServiceImp implements BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private TicketOrderRepository ticketOrderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int createBill(CheckoutDTO checkoutDTO) {
        User user = new User();
        user.setId(checkoutDTO.getUserId());
        Bill bill = new Bill();
        bill.setCreateTime(LocalDateTime.now());
        bill.setExpiredAt(LocalDateTime.now().plusMinutes(5));
        bill.setTotalAmount(checkoutDTO.getTotalPrice());
        bill.setCustomer(user);
        bill.setStatus("Pending");
        Bill billSaved=billRepository.save(bill);

        Movie movie = new Movie();
        movie.setId(checkoutDTO.getMovieId());
        Schedule schedule = scheduleRepository.findByMovieIdAndDate(checkoutDTO.getMovieId(),checkoutDTO.getSchedule());
        for(String seat: checkoutDTO.getSeats()){
            String row = seat.replaceAll("[0-9]", "");   // => "A"
            String column = seat.replaceAll("[^0-9]", ""); // => "5"
            int columnNumber = Integer.parseInt(column); // => 5
            TicketOrder ticketOrder = new TicketOrder();
            ticketOrder.setSeat(seatRepository.findSeatByMovieIdAndScheduleDateAndRowAndNumber(checkoutDTO.getMovieId(),checkoutDTO.getSchedule(),row,columnNumber));
            ticketOrder.setBill(billSaved);
            ticketOrder.setMovie(movie);
            ticketOrder.setSchedule(schedule);
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
        return billSaved.getId();
    }

    public CheckoutDTO getCheckoutDTO(int billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        CheckoutDTO dto = new CheckoutDTO();
        dto.setUserId(bill.getCustomer().getId());
        dto.setTotalPrice(bill.getTotalAmount());

        // Lấy danh sách ghế từ TicketOrder
        List<TicketOrder> ticketOrders = ticketOrderRepository.findByBillId(billId);
        if (!ticketOrders.isEmpty()) {
            dto.setMovieId(ticketOrders.get(0).getMovie().getId()); // giả sử tất cả cùng 1 movie
            dto.setSchedule(ticketOrders.get(0).getSchedule().getDate());
        }

        List<String> seatStrings = new ArrayList<>();
        for (TicketOrder ticket : ticketOrders) {
            Seat seat = ticket.getSeat();
            String seatCode = seat.getRow() + seat.getNumber();
            seatStrings.add(seatCode);
        }
        dto.setSeats(seatStrings);

        // Lấy danh sách sản phẩm từ ProductOrder
        List<ProductOrder> productOrders = productOrderRepository.findByBillId(billId);
        Map<String, Integer> productMap = new HashMap<>();
        for (ProductOrder po : productOrders) {
            productMap.put(po.getProduct().getName(), po.getNumber());
        }
        dto.setProducts(productMap);

        return dto;
    }
}
