package com.practica.demoPractica.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "[order]")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderID;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="ticketcategory_id",nullable = false)
    private TicketCategory ticketCategory;

    @Column(name = "number_of_tickets")
    private int numberOfTickets;

    @Column(name = "ordered_at")
    private Date orderedAt;

    @Column(name="total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TicketCategory getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(TicketCategory ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public Date getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(Date orderedAt) {
        this.orderedAt = orderedAt;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order() {
    }

    public Order(int orderID, Customer customer, TicketCategory ticketCategory, int numberOfTickets, Date orderedAt, BigDecimal totalPrice) {
        this.orderID = orderID;
        this.customer = customer;
        this.ticketCategory = ticketCategory;
        this.numberOfTickets = numberOfTickets;
        this.orderedAt = orderedAt;
        this.totalPrice = totalPrice;
    }
}
