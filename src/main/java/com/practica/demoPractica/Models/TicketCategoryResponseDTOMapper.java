package com.practica.demoPractica.Models;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TicketCategoryResponseDTOMapper implements Function<TicketCategory, TicketCategoryResponseDTO> {
    @Override
    public TicketCategoryResponseDTO apply(TicketCategory ticketCategory) {
        return new TicketCategoryResponseDTO(
                ticketCategory.getTicketCategoryID(),
                ticketCategory.getDescription(),
                ticketCategory.getPrice()
        );
    }
}
