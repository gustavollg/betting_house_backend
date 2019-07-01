package com.bettinghouse.api.service;

import com.bettinghouse.api.architecture.exception.ApiErrorException;
import com.bettinghouse.api.architecture.service.CRUDService;
import com.bettinghouse.api.architecture.validator.dto.ApiErrorCode;
import com.bettinghouse.api.controller.dto.EventDTO;
import com.bettinghouse.api.controller.dto.OddDTO;
import com.bettinghouse.api.model.*;
import com.bettinghouse.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService extends CRUDService<Event> {

    private EventRepository eventRepository;
    private OddRepository oddRepository;
    private BetRepository betRepository;
    private UserRepository userRepository;
    private TransactionRepository transactionRepository;
    private SportRepository sportRepository;

    @Autowired
    public EventService(EventRepository eventRepository, OddRepository oddRepository, BetRepository betRepository,
                        UserRepository userRepository, TransactionRepository transactionRepository, SportRepository sportRepository) {
        super(eventRepository);
        this.eventRepository = eventRepository;
        this.oddRepository = oddRepository;
        this.betRepository = betRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.sportRepository = sportRepository;
    }
    
    public Event getEventFromEventDTO(EventDTO eventDTO) {
        Event event = new Event();
        event.setInformation(eventDTO.getInformation());
        event.setRestricted(eventDTO.isRestricted());
        event.setOpen(true);
        event.setSport(eventDTO.getSport());
        event.setStartDate(new Date(System.currentTimeMillis()));
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
    
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }
    
    public List<Odd> findAllOddsByEventId(Long id) {
        Event event = eventRepository.findById(id).get();
        return oddRepository.findAllByEvent(event);
    }

    public void updateUserCoinsAfterEventClosed(double odd, Long eventId) {
        Optional<Event> optionalEvent = this.eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            if (!event.isOpen()) {
                throw new ApiErrorException(ApiErrorCode.EVENT_NOT_FOUND);
            }
            event.setOpen(false);
            List<Bet> bets = betRepository.findBetsByEvent(optionalEvent.get());
            bets.forEach(bet -> {
                User user = bet.getUser();
                bet.setWon(false);
                if (bet.getOdd() == odd) {
                    bet.setWon(true);
                    user.setCoins(user.getCoins() + (bet.getBet() * bet.getOdd()));
                    betRepository.save(bet);
                    transactionRepository.save(new Transaction((bet.getBet() * bet.getOdd()), user));
                    userRepository.save(user);
                }
            });
            eventRepository.save(event);
        } else {
            throw new ApiErrorException(ApiErrorCode.EVENT_NOT_FOUND);
        }
    }
    
    public List<Event> findEventsBySportId(Long id) {
        Optional<Sport> optionalSport = sportRepository.findById(id);
        if (optionalSport.isPresent()) {
            return eventRepository.findEventsBySport(optionalSport.get());
        } else {
            throw new ApiErrorException(ApiErrorCode.NOT_FOUND);
        }
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
