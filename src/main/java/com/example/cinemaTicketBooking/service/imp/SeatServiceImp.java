package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.dto.SeatDTO;
import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.repository.SeatRepository;
import com.example.cinemaTicketBooking.service.SeatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeatServiceImp implements SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;



    @Autowired
    private ObjectMapper objectMapper;

    private static final long EXPIRE_TIME = 2 * 60; // 2 phút

    public BaseResponse processSeatSelection(String message) {
        BaseResponse baseResponse = new BaseResponse();
        try{

            SeatDTO seatDTO = objectMapper.readValue(message, SeatDTO.class);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            String formattedSchedule = seatDTO.getSchedule().format(formatter);
            String key = "seat:" + seatDTO.getMovieId() + ":" + seatDTO.getSchedule() + ":" + seatDTO.getRow()+":"+ seatDTO.getNumber();

            if ("select".equals(seatDTO.getAction())) {
                String currentUser = redisTemplate.opsForValue().get(key);

                if (currentUser == null) {


                    // Ghế chưa ai giữ, tiến hành giữ
                    redisTemplate.opsForValue().set(key, String.valueOf(seatDTO.getUserId()), Duration.ofSeconds(EXPIRE_TIME));
                    seatDTO.setPrice(getSeatPrice(seatDTO));
                    baseResponse.setCode(200);
                    baseResponse.setData(seatDTO);
//                } else if (currentUser.equals(String.valueOf(seatDTO.getUserId()))) {
//                    // Người giữ ghế là chính user đang chọn lại → gia hạn giữ ghế
//                    redisTemplate.expire(key, Duration.ofSeconds(EXPIRE_TIME));
//                    baseResponse.setCode(200);
//                    baseResponse.setData(seatDTO);
                } else {

                    baseResponse.setCode(409);
                    baseResponse.setData(seatDTO);
                }
            } else if ("unselect".equals(seatDTO.getAction())) {
                redisTemplate.delete(key);
                seatDTO.setPrice(getSeatPrice(seatDTO));
                baseResponse.setCode(200);
                baseResponse.setData(seatDTO);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return baseResponse;

    }

    // Lấy trạng thái tất cả các ghế đã chọn trong phòng chiếu
    public List<SeatDTO> getCurrentSeatStatus(SeatDTO message) {
        // Mẫu key để truy xuất các ghế đã chọn trong một phòng chiếu và giờ chiếu
        String pattern = "seat:" + message.getMovieId() + ":" + message.getSchedule() + ":*";

        // Lấy tất cả các key ghế đã chọn

        Set<String> keys = redisTemplate.keys(pattern);

        // Chuyển các key thành các SeatSelectionMessage


        return getSeatDTOFromKey(keys);
    }

    public List<SeatDTO> getSelectedSeats(SeatDTO seatDTO) {
        List<SeatDTO> seatDTOs = seatRepository.findSeatsByMovieIdAndDate(seatDTO.getMovieId(),seatDTO.getSchedule()).stream().map(seat -> {
            SeatDTO sDto = new SeatDTO();
            sDto.setMovieId(seatDTO.getMovieId());
            sDto.setSchedule(seatDTO.getSchedule());
            sDto.setRow(seat.getRow());
            sDto.setNumber(seat.getNumber());
            sDto.setUserId(seat.getTicketOrder().getBill().getCustomer().getId());
            sDto.setAction("selected");
            return sDto;
        }).toList();
        return seatDTOs;
    }

    @Override
    public long getSeatPrice(SeatDTO seatDTO) {
        return seatRepository.findSeatByMovieIdAndScheduleDateAndRowAndNumber(seatDTO.getMovieId(),seatDTO.getSchedule(),seatDTO.getRow(),seatDTO.getNumber()).getTicketPrice().getPrice();
    }



    public Set<String> getSeatsByUserIdAndMovieSchedule(int userId, int movieId, LocalDateTime schedule) {
        String scheduleStr = schedule.toString(); // ISO-8601, ex: 2025-05-10T19:00
        String pattern = "seat:" + movieId + ":" + scheduleStr + ":*";


        Set<String> keys = redisTemplate.keys(pattern);  // keys(...) hỗ trợ pattern matching

        if (keys == null || keys.isEmpty()) {
            return Collections.emptySet();
        }

        // Lọc các key có value == userId
        return keys.stream()
                .filter(key -> String.valueOf(userId).equals(redisTemplate.opsForValue().get(key)))
                .collect(Collectors.toSet());
    }

    public void releaseSeats( List<SeatDTO> seats) {

        for (SeatDTO seat : seats) {
            String key = "seat:" + seat.getMovieId() + ":" + seat.getSchedule().toString() + ":" + seat.getRow() + ":" + seat.getNumber();
            String userIdInRedis = redisTemplate.opsForValue().get(key);
            redisTemplate.delete(key); // Nhả ghế
            seat.setAction("unselect");
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setCode(200);
            baseResponse.setData(seat);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            String formattedSchedule = seat.getSchedule().format(formatter);
            messagingTemplate.convertAndSend(
                    "/topic/seats/" + seat.getMovieId() + "/" + formattedSchedule,
                    baseResponse);

        }
    }

    public List<SeatDTO> getSeatDTOFromKey(Set<String> keys){
        List<SeatDTO> selectedSeats = new ArrayList<>();
        for (String key : keys) {
            String userId = redisTemplate.opsForValue().get(key);
            if (userId != null) {
                String rawData = key.substring(5);

// Tìm vị trí movieId (số đầu tiên)
                int firstColon = rawData.indexOf(":");
                String movieIdStr = rawData.substring(0, firstColon);

// Phần còn lại
                String remaining = rawData.substring(firstColon + 1);

// Tìm vị trí cuối cùng của `:` trước row
                int lastColon = remaining.lastIndexOf(":");
                String numberStr = remaining.substring(lastColon + 1);
                remaining = remaining.substring(0, lastColon);

                lastColon = remaining.lastIndexOf(":");
                String rowStr = remaining.substring(lastColon + 1);
                String scheduleStr = remaining.substring(0, lastColon);

// Gán vào DTO
                SeatDTO seatDTO = new SeatDTO();
                seatDTO.setMovieId(Integer.parseInt(movieIdStr));
                seatDTO.setSchedule(LocalDateTime.parse(scheduleStr));
                seatDTO.setRow(rowStr);
                seatDTO.setNumber(Integer.parseInt(numberStr));
                seatDTO.setUserId(Integer.parseInt(userId));
                seatDTO.setPrice(getSeatPrice(seatDTO));
                seatDTO.setAction("select");
                selectedSeats.add(seatDTO);
            }
        }
        return selectedSeats;
    }

    public void resetTime(Set<String> keys){
        for (String key : keys) {
            redisTemplate.expire(key, Duration.ofSeconds(2*60));
        }
    }


}
