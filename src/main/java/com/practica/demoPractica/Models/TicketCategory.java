package com.practica.demoPractica.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ticket_category")
public class TicketCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketcategory_id")
    private int ticketCategoryID;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "description")
    private String description;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    public int getTicketCategoryID() {
        return ticketCategoryID;
    }

    public void setTicketCategoryID(int ticketCategoryID) {
        this.ticketCategoryID = ticketCategoryID;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TicketCategory{" +
                "ticketCategoryID=" + ticketCategoryID +
                ", event=" + event +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public TicketCategory() {
    }
}
