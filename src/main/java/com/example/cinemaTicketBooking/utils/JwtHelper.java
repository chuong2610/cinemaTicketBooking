package com.example.cinemaTicketBooking.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtHelper {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(int id,String data) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        return Jwts.builder().setSubject(data).id(String.valueOf(id)).signWith(key).compact();
    }

    public int getIdUserFromToken(String token){
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        try{
            String id = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getId();
            return Integer.parseInt(id);
        }catch (Exception e){
            throw new RuntimeException("Error " + e.getMessage());
        }
    }

    public String decodeToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        String data="";
        try {
            data = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
        }catch (ExpiredJwtException e){
            System.out.println("Token is expired");
        }catch (JwtException e){
            System.out.println("Decode error");
        }
        return data;
    }
}
