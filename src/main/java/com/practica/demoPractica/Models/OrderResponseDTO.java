package com.practica.demoPractica.Models;

import java.math.BigDecimal;
import java.util.Date;

public record OrderResponseDTO(
        int eventID,
        Date orderedAt,
        int ticketCategoryID,
        int numberOfTickets,
        BigDecimal totalPrice
) {
}
