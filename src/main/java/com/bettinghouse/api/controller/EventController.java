package com.bettinghouse.api.controller;

import com.bettinghouse.api.architecture.controller.CRUDController;
import com.bettinghouse.api.controller.dto.EventDTO;
import com.bettinghouse.api.model.Event;
import com.bettinghouse.api.model.Odd;
import com.bettinghouse.api.service.EventService;
import com.bettinghouse.api.validator.EventValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventController extends CRUDController<Event> {
    
    private EventValidator eventValidator;
    private EventService eventService;

    @Autowired
    public EventController(EventValidator eventValidator, EventService eventService) {
        super(eventValidator, eventService);
        this.eventValidator = eventValidator;
        this.eventService = eventService;
    }
    
    @PostMapping("save")
    public ResponseEntity<Event> saveEvent(@RequestBody @Valid EventDTO eventDTO) {
        eventValidator.validateBeforeSaveEventDTO(eventDTO);
        Event event = eventService.getEventFromEventDTO(eventDTO);
        Event eventPersisted = eventService.save(event);
        eventService.executeAfterSaveEventFromEventDTO(event, eventDTO.getOddDTOs());
        return ResponseEntity.ok(eventPersisted);
    }
    
    @GetMapping("find-all-open-events")
    public ResponseEntity<List<Event>> findAllOpenEvents() {
        List<Event> events = eventService.findAllOpenEvents();
        List<Event> openEvents = events.stream().filter(Event::isOpen).collect(Collectors.toList());
        return ResponseEntity.ok(openEvents);
    }
    
    @GetMapping("find-all-odds/{id}")
    public ResponseEntity<List<Odd>> findAllOddsByEventId(@PathVariable Long id) {
        List<Odd> odds = eventService.findAllOddsByEventId(id);
        return ResponseEntity.ok(odds);
    }
}
