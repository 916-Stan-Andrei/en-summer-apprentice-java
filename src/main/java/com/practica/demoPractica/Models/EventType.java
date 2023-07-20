package com.practica.demoPractica.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "event_type")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="eventtype_id")
    private int eventTypeID;
    @Column(name = "name", unique = true)
    private String name;

    public int getEventTypeID() {
        return eventTypeID;
    }

    public void setEventTypeID(int eventTypeID) {
        this.eventTypeID = eventTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EventType{" +
                "eventTypeID=" + eventTypeID +
                ", name='" + name + '\'' +
                '}';
    }

    public EventType() {
    }
}
