package com.practica.demoPractica.Service;

import com.practica.demoPractica.Models.*;
import com.practica.demoPractica.Repository.CustomerRepository;
import com.practica.demoPractica.Repository.OrderRepository;
import com.practica.demoPractica.Repository.TicketCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderResponseDTOMapper orderResponseDTOMapper;

    @Autowired
    private TicketCategoryRepository ticketCategoryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Order getOrderByID(int orderID) {return orderRepository.findById(orderID).orElse(null);}

    public List<OrderResponseDTO> getOrders(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .filter(order -> order.getCustomer().getCustomerID() == 1)
                .map(orderResponseDTOMapper)
                .collect(Collectors.toList());
    }

    public OrderResponseDTO saveOrder(OrderRequestDTO orderRequestDTO){
        Order order = new Order();
        Date currentDate = new Date();
        order.setOrderedAt(currentDate);
        order.setCustomer(customerRepository.findById(1).orElse(null));
        TicketCategory ticketCategory = ticketCategoryRepository.findById(orderRequestDTO.ticketCategoryID()).orElse(null);
        if (ticketCategory == null || orderRequestDTO.eventID() != ticketCategory.getEvent().getEventID()) {
            throw new IllegalArgumentException("Invalid ticket category or event ID");
        }
        order.setTicketCategory(ticketCategory);
        order.setTotalPrice(order.getTicketCategory().getPrice().multiply(BigDecimal.valueOf(orderRequestDTO.numberOfTickets())));
        order.setNumberOfTickets(orderRequestDTO.numberOfTickets());
        orderRepository.save(order);
        return orderResponseDTOMapper.apply(order);
    }
}
