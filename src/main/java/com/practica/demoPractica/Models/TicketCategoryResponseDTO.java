package com.practica.demoPractica.Models;

import java.math.BigDecimal;

public record TicketCategoryResponseDTO(
        int ticketCategoryID,
        String description,
        BigDecimal price
) {
}
