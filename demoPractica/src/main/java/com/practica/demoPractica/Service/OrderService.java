package com.practica.demoPractica.Service;

import com.practica.demoPractica.Models.Order;
import com.practica.demoPractica.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderByID(int orderID) {return orderRepository.findById(orderID).orElse(null);}

    public List<Order> getOrders(){return orderRepository.findAll();}
}
