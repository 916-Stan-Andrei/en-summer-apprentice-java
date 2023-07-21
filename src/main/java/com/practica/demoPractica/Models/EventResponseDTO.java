package com.practica.demoPractica.Models;

import java.util.Date;
import java.util.List;

public record EventResponseDTO(
    int eventID,
    Location location,
    String type,
    String description,
    String name,
    Date startDate,
    Date endDate,
    List<TicketCategoryResponseDTO> ticketCategories
){

}