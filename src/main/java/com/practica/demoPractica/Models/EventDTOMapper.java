package com.practica.demoPractica.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EventDTOMapper implements Function<Event, EventDTO> {
    @Autowired
    TicketCategoryDTOMapper ticketCategoryDTOMapper;

    @Override
    public EventDTO apply(Event event) {
        List<TicketCategoryDTO> ticketCategoryDTOs = event.getTicketCategories()
                .stream()
                .map(ticketCategoryDTOMapper)
                .collect(Collectors.toList());

        return new EventDTO(
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
