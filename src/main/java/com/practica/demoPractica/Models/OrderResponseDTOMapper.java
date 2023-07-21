package com.practica.demoPractica.Models;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderResponseDTOMapper implements Function<Order, OrderResponseDTO> {
    @Override
    public OrderResponseDTO apply(Order order) {
        return new OrderResponseDTO(
                order.getTicketCategory().getEvent().getEventID(),
                order.getOrderedAt(),
                order.getTicketCategory().getTicketCategoryID(),
                order.getNumberOfTickets(),
                order.getTotalPrice()
        );
    }
}
