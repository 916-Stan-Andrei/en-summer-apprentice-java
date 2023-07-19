package com.practica.demoPractica.api;

import com.practica.demoPractica.Models.Event;
import com.practica.demoPractica.Service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<Event> getAllUsers(){return eventService.getAllEvents();}

    @GetMapping("/event/{id}")
    public Event getUser(@PathVariable int id){return eventService.getEventByID(id);}

    @PostMapping("/eventpost")
    public void saveEvent(@RequestBody Event event){eventService.saveEvent(event);}

}
