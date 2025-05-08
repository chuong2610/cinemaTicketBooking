package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.dto.CardDTO;
import com.example.cinemaTicketBooking.dto.UserDTO;
import com.example.cinemaTicketBooking.entity.Card;
import com.example.cinemaTicketBooking.entity.User;
import com.example.cinemaTicketBooking.repository.UserRepository;
import com.example.cinemaTicketBooking.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDTO findById(int id) {
        User user = userRepository.findById(id).get();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setPhone(user.getPhone());

        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(user.getCard().getId());
        cardDTO.setName(user.getCard().getName());
        cardDTO.setPoint(user.getCard().getPoint());
        userDTO.setCardDTO(cardDTO);
        return userDTO;
    }

    @Override
    public void syncTemporaryUserToRealUser(int realUserId, int tempUserId, int movieId, LocalDateTime schedule) {

    }
}
