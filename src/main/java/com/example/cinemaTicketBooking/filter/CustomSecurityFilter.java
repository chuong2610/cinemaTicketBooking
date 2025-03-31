package com.example.cinemaTicketBooking.filter;

import com.example.cinemaTicketBooking.utils.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomSecurityFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHelper jwtHelper;


    @Override
    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        String authenHeader = req.getHeader("Authorization");
        if(authenHeader != null && authenHeader.startsWith("Bearer ")){
            String token = authenHeader.substring(7);
            String roleName = jwtHelper.decodeToken(token);
            if(!roleName.equals("")){
                List<GrantedAuthority> roles = new ArrayList<>();
                SimpleGrantedAuthority role = new SimpleGrantedAuthority(roleName);
                roles.add(role);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("","",roles);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }


        }
        filterChain.doFilter(req, res);
    }
}
