package com.practica.demoPractica.Service;

import com.practica.demoPractica.Models.Event;
import com.practica.demoPractica.Models.EventResponseDTO;
import com.practica.demoPractica.Models.EventResponseDTOMapper;
import com.practica.demoPractica.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventResponseDTOMapper eventDTOMapper;

    public Event getEventByID(int eventID){ return eventRepository.findById(eventID).orElse(null);}

    public List<Event> getAllEvents(){return eventRepository.findAll();}

    public void saveEvent(Event event){eventRepository.save(event);}

    public List<EventResponseDTO> getAllEventsByLocationIDAndEventType(int locationID, String eventType){
        List<Event> events = eventRepository.findAll();
        List<EventResponseDTO> eventResponseDTOList = events.stream()
                .filter(event -> event.getLocation().getLocationID() == locationID &&
                        Objects.equals(event.getEventType().getName(), eventType))
                .map(eventDTOMapper)
                .collect(Collectors.toList());
        if (eventResponseDTOList.isEmpty())
            throw new IllegalArgumentException("No events found for the given location and event type!");
        return eventResponseDTOList;
    }

}
