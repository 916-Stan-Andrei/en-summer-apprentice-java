package com.practica.demoPractica.Models;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TicketCategoryDTOMapper implements Function<TicketCategory, TicketCategoryDTO> {
    @Override
    public TicketCategoryDTO apply(TicketCategory ticketCategory) {
        return new TicketCategoryDTO(
                ticketCategory.getTicketCategoryID(),
                ticketCategory.getDescription(),
                ticketCategory.getPrice()
        );
    }
}
