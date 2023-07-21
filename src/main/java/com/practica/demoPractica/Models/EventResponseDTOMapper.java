package com.practica.demoPractica.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EventResponseDTOMapper implements Function<Event, EventResponseDTO> {
    @Autowired
    TicketCategoryResponseDTOMapper ticketCategoryDTOMapper;

    @Override
    public EventResponseDTO apply(Event event) {
        List<TicketCategoryResponseDTO> ticketCategoryDTOs = event.getTicketCategories()
                .stream()
                .map(ticketCategoryDTOMapper)
                .collect(Collectors.toList());

        return new EventResponseDTO(
                event.getEventID(),
                event.getLocation(),
                event.getEventType().getName(),
                event.getDescription(),
                event.getName(),
                event.getStartDate(),
                event.getEndDate(),
                ticketCategoryDTOs
        );
    }
}
