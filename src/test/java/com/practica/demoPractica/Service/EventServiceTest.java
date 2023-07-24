package com.practica.demoPractica.Service;

import com.practica.demoPractica.Models.*;
import com.practica.demoPractica.Repository.EventRepository;
import io.micrometer.observation.Observation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventResponseDTOMapper eventResponseDTOMapper;

    @InjectMocks
    private EventService eventService;

    @Test
    void getEventByID() {
        when(eventRepository.findById(anyInt())).thenReturn(Optional.of(new Event()));
        Event resultEvent = eventService.getEventByID(0);
        assertNotNull(resultEvent);
        assertEquals(0, resultEvent.getEventID());

        when(eventRepository.findById(anyInt())).thenReturn(Optional.of(new Event(1,
                new Location(1, "location1", "type1", 1000),
                new EventType(1, "type"),
                "event", "description", new Date(), new Date(), null
        )));
        Event resultEvent2 = eventService.getEventByID(1);
        assertNotNull(resultEvent2);
        assertEquals(1, resultEvent2.getEventID());
        assertEquals(1, resultEvent2.getLocation().getLocationID());
        assertEquals("location1", resultEvent2.getLocation().getName());
        assertEquals("type1", resultEvent2.getLocation().getType());
        assertEquals(1000, resultEvent2.getLocation().getCapacity());
        assertEquals(1, resultEvent2.getEventType().getEventTypeID());
        assertEquals("type", resultEvent2.getEventType().getName());
        assertEquals("event", resultEvent2.getName());
        assertEquals("description", resultEvent2.getDescription());
        assertNotNull(resultEvent2.getStartDate());
        assertNotNull(resultEvent2.getEndDate());
        assertNull(resultEvent2.getTicketCategories());
    }

    @Test
    void getAllEvents() {
        when(eventRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Event(), new Event(1,
                                new Location(1, "location1", "type1", 1000),
                                new EventType(1, "type"),
                                "event", "description", new Date(), new Date(), null
                        )
                ));
        List<Event> events = eventService.getAllEvents();
        assertFalse(events.isEmpty());
        assertEquals(2, events.size());
        assertEquals(0, events.get(0).getEventID());
        assertEquals(1, events.get(1).getEventID());
    }

    @Test
    void saveEvent() {
        Event event = new Event(1,
                new Location(1, "location1", "type1", 1000),
                new EventType(1, "type"),
                "event", "description", new Date(), new Date(), null
        );

        eventService.saveEvent(event);
        verify(eventRepository, times(1)).save(event);

        ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
        verify(eventRepository).save(eventCaptor.capture());

        Event savedEvent = eventCaptor.getValue();
        assertNotNull(savedEvent);
        assertEquals(event.getEventID(), savedEvent.getEventID());
        assertEquals(event.getLocation(), savedEvent.getLocation());
        assertEquals(event.getEventType(), savedEvent.getEventType());
        assertEquals(event.getDescription(), savedEvent.getDescription());
    }

    @Test
    void getAllEventsByLocationIDAndEventType() {
        Location location1 = new Location(1, "location1", "type1", 1000);
        Location location2 = new Location(2, "location2", "type2", 2000);
        EventType eventType1 = new EventType(1, "name1");
        EventType eventType2 = new EventType(2, "name2");

        List<Event> events = Arrays.asList(
                new Event(1, location1, eventType1, "event1", "description1", new Date(), new Date(), null),
                new Event(2, location1, eventType1, "event2", "description2", new Date(), new Date(), null),
                new Event(3, location2, eventType2, "event3", "description3", new Date(), new Date(), null)
        );

        when(eventRepository.findAll()).thenReturn(events);
        when(eventResponseDTOMapper.apply(ArgumentMatchers.any(Event.class)))
                .thenAnswer(invocation -> {
                    Event event = invocation.getArgument(0);
                    return new EventResponseDTO(event.getEventID(), event.getLocation(), event.getEventType().getName(),
                            event.getDescription(), event.getName(), event.getStartDate(), event.getEndDate(), null
                    );
                });

        List<EventResponseDTO> resultEventList1 = eventService.getAllEventsByLocationIDAndEventType(1, "name1");
        assertFalse(resultEventList1.isEmpty());
        assertEquals(2, resultEventList1.size());

        //Check the attributes for the first element in the resulted list
        assertEquals(1, resultEventList1.get(0).eventID());
        assertEquals(location1, resultEventList1.get(0).location());
        assertEquals(eventType1.getName(), resultEventList1.get(0).type());
        assertEquals("description1", resultEventList1.get(0).description());
        assertEquals("event1", resultEventList1.get(0).name());
        assertNotNull(resultEventList1.get(0).startDate());
        assertNotNull(resultEventList1.get(0).endDate());
        assertNull(resultEventList1.get(0).ticketCategories());

        //Check the attributes for the second element in the resulted list
        assertEquals(2, resultEventList1.get(1).eventID());
        assertEquals(location1, resultEventList1.get(1).location());
        assertEquals(eventType1.getName(), resultEventList1.get(1).type());
        assertEquals("description2", resultEventList1.get(1).description());
        assertEquals("event2", resultEventList1.get(1).name());
        assertNotNull(resultEventList1.get(1).startDate());
        assertNotNull(resultEventList1.get(1).endDate());
        assertNull(resultEventList1.get(1).ticketCategories());

        List<EventResponseDTO> resultEventList2 = eventService.getAllEventsByLocationIDAndEventType(2, "name2");
        assertFalse(resultEventList2.isEmpty());
        assertEquals(1, resultEventList2.size());
        assertEquals(3, resultEventList2.get(0).eventID());
        assertEquals(location2, resultEventList2.get(0).location());
        assertEquals(eventType2.getName(), resultEventList2.get(0).type());
        assertEquals("description3", resultEventList2.get(0).description());
        assertEquals("event3", resultEventList2.get(0).name());
        assertNotNull(resultEventList2.get(0).startDate());
        assertNotNull(resultEventList2.get(0).endDate());
        assertNull(resultEventList2.get(0).ticketCategories());

        assertThatThrownBy(() -> {
            eventService.getAllEventsByLocationIDAndEventType(3, "name3");
        }).isInstanceOf(IllegalArgumentException.class);

        verify(eventResponseDTOMapper, times(3)).apply(any(Event.class));
    }
}