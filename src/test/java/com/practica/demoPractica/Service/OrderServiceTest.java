package com.practica.demoPractica.Service;

import com.practica.demoPractica.Models.*;
import com.practica.demoPractica.Repository.CustomerRepository;
import com.practica.demoPractica.Repository.OrderRepository;
import com.practica.demoPractica.Repository.TicketCategoryRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderResponseDTOMapper orderResponseDTOMapper;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TicketCategoryRepository ticketCategoryRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void getOrderByID() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(new Order()));
        Order resultedOrder = orderService.getOrderByID(0);
        assertNotNull(resultedOrder);
        assertEquals(0, resultedOrder.getOrderID());

        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(
           new Order(1, new Customer(), new TicketCategory(), 10, new Date(), new BigDecimal(100))));
        Order resultedOrder2 = orderService.getOrderByID(1);
        assertNotNull(resultedOrder2);
        assertEquals(1, resultedOrder2.getOrderID());
        assertNotNull(resultedOrder2.getCustomer());
        assertNotNull(resultedOrder2.getTicketCategory());
        assertEquals(10, resultedOrder2.getNumberOfTickets());
        assertNotNull(resultedOrder2.getOrderedAt());
        assert resultedOrder2.getTotalPrice().equals(new BigDecimal(100));
    }

    @Test
    void getOrders() {
        Customer customer1 = new Customer(1, "email1", "name1");
        Customer customer2 = new Customer(2, "email2", "name2");

        List<Order> orders = Arrays.asList(
                new Order(1, customer1, new TicketCategory(), 10, new Date(), new BigDecimal(100)),
                new Order(2, customer2, new TicketCategory(), 20, new Date(), new BigDecimal(200)),
                new Order(3, customer1, new TicketCategory(), 30, new Date(), new BigDecimal(300))
        );

        when(orderRepository.findAll()).thenReturn(orders);
        when(orderResponseDTOMapper.apply(ArgumentMatchers.any(Order.class)))
                .thenAnswer(invocation -> {
                    Order order = invocation.getArgument(0);
                    return new OrderResponseDTO(
                            0,
                            order.getOrderedAt(),
                            order.getTicketCategory().getTicketCategoryID(),
                            order.getNumberOfTickets(),
                            order.getTotalPrice());});

        List<OrderResponseDTO> resultedOrders1 = orderService.getOrders();
        assertFalse(resultedOrders1.isEmpty());
        assertEquals(2, resultedOrders1.size());
        assertEquals(10, resultedOrders1.get(0).numberOfTickets());
        assertEquals(30, resultedOrders1.get(1).numberOfTickets());

        verify(orderRepository, times(1)).findAll();
        verify(orderResponseDTOMapper, times(2)).apply(any(Order.class));
    }

    @Test
    void saveOrder() {
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(1, 1, 10);

        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(new Customer(1, "email", "cusName")));
        when(ticketCategoryRepository.findById(anyInt())).thenReturn(Optional.of(
                new TicketCategory(1, new Event(1, new Location(), new EventType(), "name", "desc", new Date(), new Date(), null)
                        , "ticketdesc", new BigDecimal(100)
                )));
        when(orderResponseDTOMapper.apply(ArgumentMatchers.any(Order.class)))
                .thenAnswer(invocationOnMock -> {
                    Order order = invocationOnMock.getArgument(0);
                    return new OrderResponseDTO(
                            order.getTicketCategory().getEvent().getEventID(),
                            order.getOrderedAt(),
                            order.getTicketCategory().getTicketCategoryID(),
                            order.getNumberOfTickets(),
                            order.getTotalPrice());});

        when(orderRepository.save(any(Order.class))).thenReturn(new Order());

        OrderResponseDTO orderResponseDTO = orderService.saveOrder(orderRequestDTO);
        verify(orderResponseDTOMapper, times(1)).apply(any(Order.class));
        verify(orderRepository, times(1)).save(any(Order.class));

    }
}