package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.dto.ActorDTO;
import com.example.cinemaTicketBooking.entity.Actor;
import com.example.cinemaTicketBooking.repository.ActorRepository;
import com.example.cinemaTicketBooking.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImp implements ActorService {
    @Autowired
    private ActorRepository actorRepository;
    @Override
    public ActorDTO findById(int id) {
        Actor actor = actorRepository.findById(id).get();
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setId(actor.getId());
        actorDTO.setName(actor.getName());
        actorDTO.setDescription(actor.getDescription());

        return actorDTO;
    }
}
