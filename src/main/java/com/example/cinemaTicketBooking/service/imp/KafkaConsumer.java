package com.example.cinemaTicketBooking.service.imp;


import com.example.cinemaTicketBooking.dto.SeatDTO;
import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.service.SeatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class KafkaConsumer {

    @Autowired
    private SeatService seatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private final ObjectMapper objectMapper;

    public KafkaConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "seat-selection-topic", groupId = "seat-group")
    public void consume(String message) {
        try {


            SeatDTO seatDTO = objectMapper.readValue(message, SeatDTO.class);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            String formattedSchedule = seatDTO.getSchedule().format(formatter);
            // Xử lý lưu trạng thái vào Redis
            BaseResponse baseResponse= seatService.processSeatSelection(message);

            // Push ngược lại cho client (broadcast)

            messagingTemplate.convertAndSend(
                    "/topic/seats/" + seatDTO.getMovieId() + "/" + formattedSchedule,
                    baseResponse
            );
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}

