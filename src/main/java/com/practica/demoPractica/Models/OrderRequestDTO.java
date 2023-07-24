package com.practica.demoPractica.Models;

public record OrderRequestDTO(
        int eventID,
        int ticketCategoryID,
        int numberOfTickets
) {
}
