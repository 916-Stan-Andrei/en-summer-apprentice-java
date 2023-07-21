package com.practica.demoPractica.api;

import com.practica.demoPractica.Models.Order;
import com.practica.demoPractica.Models.OrderRequestDTO;
import com.practica.demoPractica.Models.OrderResponseDTO;
import com.practica.demoPractica.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<OrderResponseDTO> getOrders(){return orderService.getOrders();}

    @PostMapping("/addorder")
    public OrderResponseDTO save(@RequestBody OrderRequestDTO orderRequestDTO){return orderService.saveOrder(orderRequestDTO);}
}
