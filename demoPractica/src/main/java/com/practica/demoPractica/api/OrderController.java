package com.practica.demoPractica.api;

import com.practica.demoPractica.Models.Order;
import com.practica.demoPractica.Service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/{id}")
    public Order getOrder(@PathVariable int id){return orderService.getOrderByID(id);}

    @GetMapping("/orders")
    public List<Order> getOrders(){return orderService.getOrders();}
}
