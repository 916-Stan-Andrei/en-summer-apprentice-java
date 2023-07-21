package com.practica.demoPractica.api;

import com.practica.demoPractica.Models.Event;
import com.practica.demoPractica.Models.EventResponseDTO;
import com.practica.demoPractica.Service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/allevents")
    public List<Event> getAllEvents(){return eventService.getAllEvents();}

    @GetMapping("/event/{id}")
    public Event getEvent(@PathVariable int id){return eventService.getEventByID(id);}

    @GetMapping("/events")
    public List<EventResponseDTO> getEventsByLocationIDAndEventType(@RequestParam(name = "locId") int locationID,
                                                                    @RequestParam(name = "eventType") String eventType){
        return eventService.getAllEventsByLocationIDAndEventType(locationID, eventType);
    }

    @PostMapping("/eventpost")
    public void saveEvent(@RequestBody Event event){eventService.saveEvent(event);}

}
