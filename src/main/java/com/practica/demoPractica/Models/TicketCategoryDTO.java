package com.practica.demoPractica.Models;

import java.math.BigDecimal;

public record TicketCategoryDTO(
        int ticketCategoryID,
        String description,
        BigDecimal price
) {
}
