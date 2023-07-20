package com.practica.demoPractica.Service;

import com.practica.demoPractica.Models.Event;
import com.practica.demoPractica.Models.EventDTO;
import com.practica.demoPractica.Models.EventDTOMapper;
import com.practica.demoPractica.Repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository eventRepository;

    private final EventDTOMapper eventDTOMapper;

    public EventService(EventRepository eventRepository, EventDTOMapper eventDTOMapper) {
        this.eventRepository = eventRepository;
        this.eventDTOMapper = eventDTOMapper;
    }
    public Event getEventByID(int eventID){ return eventRepository.findById(eventID).orElse(null);}

    public List<Event> getAllEvents(){return eventRepository.findAll();}

    public void saveEvent(Event event){eventRepository.save(event);}

    public List<EventDTO> getAllEventsByLocationIDAndEventType(int locationID, String eventType){
        List<Event> events = eventRepository.findAll();

        return events.stream()
                .filter(event -> event.getLocation().getLocationID() == locationID &&
                        Objects.equals(event.getEventType().getName(), eventType))
                .map(eventDTOMapper)
                .collect(Collectors.toList());
    }

}
