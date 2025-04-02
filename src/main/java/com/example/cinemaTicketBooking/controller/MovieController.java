package com.example.cinemaTicketBooking.controller;


import com.example.cinemaTicketBooking.dto.MovieDTO;
import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/now-showing")
    public ResponseEntity<?> nowShowing(@RequestParam int page, @RequestParam int size) {
        List<MovieDTO> movieDTOS= movieService.findByDateBefore(page, size);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(movieDTOS);
        baseResponse.setMessage("Now showing");
        baseResponse.setCode(200);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<?> upcoming(@RequestParam int page, @RequestParam int size) {
        List<MovieDTO> movieDTOS= movieService.findByDateAfter(page, size);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(movieDTOS);
        baseResponse.setMessage("upcoming");
        baseResponse.setCode(200);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/top-movie")
    public ResponseEntity<?> topMovie(@RequestParam int page, @RequestParam int size) {
        List<MovieDTO> movieDTOS= movieService.findTopMoviesByReviewCount(page, size);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(movieDTOS);
        baseResponse.setMessage("top movie");
        baseResponse.setCode(200);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/movie-detail")
    public ResponseEntity<?> topMovie(@RequestParam int id) {

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(movieService.findById(id));
        baseResponse.setMessage("movie_detail");
        baseResponse.setCode(200);
        return ResponseEntity.ok(baseResponse);
    }


}
