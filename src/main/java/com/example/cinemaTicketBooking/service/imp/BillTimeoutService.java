package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.entity.Bill;
import com.example.cinemaTicketBooking.repository.BillRepository;
import com.example.cinemaTicketBooking.repository.SeatRepository;
import com.example.cinemaTicketBooking.repository.TicketOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillTimeoutService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private TicketOrderRepository ticketOrderRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Scheduled(fixedRate = 60000) // mỗi 60 giây
    @Transactional
    public void cancelExpiredBills() {
        List<Bill> expiredBills = billRepository.findByStatusAndExpiredAtBefore("Pending", LocalDateTime.now());

        for (Bill bill : expiredBills) {
            bill.setStatus("FAILED");
            billRepository.save(bill);

            // Xoá ticketOrder
            ticketOrderRepository.deleteByBillId(bill.getId());

            // Cập nhật lại ghế về trạng thái chưa đặt
            seatRepository.updateSeatStatusByBillId(bill.getId(), 0); // set lại 0 nếu 0 là "available"
        }
    }
}
