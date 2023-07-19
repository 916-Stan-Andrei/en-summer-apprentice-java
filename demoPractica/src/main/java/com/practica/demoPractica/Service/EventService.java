package com.practica.demoPractica.Service;

import com.practica.demoPractica.Models.Event;
import com.practica.demoPractica.Repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public Event getEventByID(int eventID){ return eventRepository.findById(eventID).orElse(null);}

    public List<Event> getAllEvents(){return eventRepository.findAll();}

    public void saveEvent(Event event){eventRepository.save(event);}

}
