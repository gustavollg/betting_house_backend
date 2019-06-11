package com.bettinghouse.api.service;

import com.bettinghouse.api.architecture.service.CRUDService;
import com.bettinghouse.api.model.Event;
import com.bettinghouse.api.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService extends CRUDService<Event> {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        super(eventRepository);
        this.eventRepository = eventRepository;
    }

    @Override
    public void executeBeforeSave(Event entity) {
        
    }

    @Override
    public void executeAfterSave(Event entity) {

    }

    @Override
    public void executeBeforeDelete(Event entity) {

    }

    @Override
    public void executeAfterDelete(Event entity) {

    }
}
