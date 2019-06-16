package com.bettinghouse.api.controller;

import com.bettinghouse.api.architecture.controller.CRUDController;
import com.bettinghouse.api.controller.dto.EventDTO;
import com.bettinghouse.api.model.Event;
import com.bettinghouse.api.service.EventService;
import com.bettinghouse.api.validator.EventValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
