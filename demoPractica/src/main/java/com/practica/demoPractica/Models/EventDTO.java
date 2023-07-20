package com.practica.demoPractica.Models;

import java.sql.Timestamp;
import java.util.List;

public record EventDTO (
    int eventID,
    Location location,
    String type,
    String description,
    String name,
    Timestamp startDate,
    Timestamp endDate,
    List<TicketCategoryDTO> ticketCategories
){

}