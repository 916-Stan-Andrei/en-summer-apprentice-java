package com.practica.demoPractica.Models;

import java.math.BigDecimal;

public record OrderRequestDTO(
        int eventID,
        int ticketCategoryID,
        int numberOfTickets
) {
}
