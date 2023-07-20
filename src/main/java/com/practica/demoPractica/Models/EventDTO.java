package com.practica.demoPractica.Models;

import java.util.Date;
import java.util.List;

public record EventDTO (
    int eventID,
    Location location,
    String type,
    String description,
    String name,
    Date startDate,
    Date endDate,
    List<TicketCategoryDTO> ticketCategories
){

}