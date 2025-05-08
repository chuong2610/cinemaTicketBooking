package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.entity.User;
import com.example.cinemaTicketBooking.repository.UserRepository;
import com.example.cinemaTicketBooking.service.AuthenticationService;
import com.example.cinemaTicketBooking.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImp implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public String authenticate(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        String token="";
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            if(passwordEncoder.matches(password, user.getPassword())) {
                token=jwtHelper.generateToken(user.getId(),user.getRole().getName());
            }
        }
        return token;
    }
}
