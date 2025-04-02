package com.example.cinemaTicketBooking.controller;

import com.example.cinemaTicketBooking.entity.Genre;
import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.repository.GenreRepository;
import com.example.cinemaTicketBooking.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/genres")
@RestController
public class GenreController {
    @Autowired
    private GenreService genreService;
    @GetMapping
    public ResponseEntity<?> genre(@RequestParam int page, @RequestParam int size) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("List of genres");
        baseResponse.setCode(200);
        baseResponse.setData(genreService.findGenres(page, size));
        return ResponseEntity.ok(baseResponse);
    }
}
