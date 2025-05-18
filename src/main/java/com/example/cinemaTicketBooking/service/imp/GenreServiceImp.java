package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.dto.GenreDTO;
import com.example.cinemaTicketBooking.repository.GenreRepository;
import com.example.cinemaTicketBooking.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImp implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public List<GenreDTO> findGenres(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<GenreDTO> GenreDTOs = genreRepository.findAll(pageable).stream().map(genre -> {
            GenreDTO GenreDTO = new GenreDTO();
            GenreDTO.setId(genre.getId());
            GenreDTO.setName(genre.getName());
            return GenreDTO;
        }).toList();
        return GenreDTOs;
    }
}
