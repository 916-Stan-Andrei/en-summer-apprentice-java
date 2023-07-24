package com.practica.demoPractica.api;

import com.practica.demoPractica.Models.Event;
import com.practica.demoPractica.Models.EventResponseDTO;
import com.practica.demoPractica.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;


    @GetMapping("/allevents")
    public List<Event> getAllEvents(){return eventService.getAllEvents();}

    @GetMapping("/event/{id}")
    public Event getEvent(@PathVariable int id){return eventService.getEventByID(id);}

    @GetMapping("/events")
    public List<EventResponseDTO> getEventsByLocationIDAndEventType(@RequestParam(name = "locId") int locationID,
                                                                    @RequestParam(name = "eventType") String eventType){
        return eventService.getAllEventsByLocationIDAndEventType(locationID, eventType);
    }

    @PostMapping("/event")
    public void saveEvent(@RequestBody Event event){eventService.saveEvent(event);}

}
