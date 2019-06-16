package com.bettinghouse.api.service;

import com.bettinghouse.api.architecture.service.CRUDService;
import com.bettinghouse.api.controller.dto.EventDTO;
import com.bettinghouse.api.controller.dto.OddDTO;
import com.bettinghouse.api.model.Event;
import com.bettinghouse.api.model.Odd;
import com.bettinghouse.api.repository.EventRepository;
import com.bettinghouse.api.repository.OddRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService extends CRUDService<Event> {

    private EventRepository eventRepository;
    private OddRepository oddRepository;

    @Autowired
    public EventService(EventRepository eventRepository, OddRepository oddRepository) {
        super(eventRepository);
        this.eventRepository = eventRepository;
        this.oddRepository = oddRepository;
    }
    
    public Event getEventFromEventDTO(EventDTO eventDTO) {
        Event event = new Event();
        event.setInformation(eventDTO.getInformation());
        event.setRestricted(eventDTO.isRestricted());
        event.setOpen(true);
        event.setSport(eventDTO.getSport());
        event.setHasDraw(eventDTO.isHasDraw());
        return event;
    }
    
    public void executeAfterSaveEventFromEventDTO(Event event, List<OddDTO> oddDTOs) {
        List<Odd> odds = new ArrayList<>();
        oddDTOs.forEach(oddDTO -> {
            Odd odd = new Odd();
            odd.setEvent(event);
            odd.setOdd(oddDTO.getOdd());
            odd.setTeam(oddDTO.getTeam());
            odds.add(odd);
        });
        oddRepository.saveAll(odds);
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
