package com.bettinghouse.api.service;

import com.bettinghouse.api.architecture.exception.ApiErrorException;
import com.bettinghouse.api.architecture.service.CRUDService;
import com.bettinghouse.api.architecture.util.AuthenticationUtil;
import com.bettinghouse.api.architecture.validator.dto.ApiErrorCode;
import com.bettinghouse.api.controller.dto.BetDTO;
import com.bettinghouse.api.controller.dto.HistoryDTO;
import com.bettinghouse.api.model.Bet;
import com.bettinghouse.api.model.Event;
import com.bettinghouse.api.model.Transaction;
import com.bettinghouse.api.model.User;
import com.bettinghouse.api.repository.BetRepository;
import com.bettinghouse.api.repository.EventRepository;
import com.bettinghouse.api.repository.TransactionRepository;
import com.bettinghouse.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BetService extends CRUDService<Bet> {
    
    private BetRepository betRepository;
    private UserRepository userRepository;
    private EventRepository eventRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public BetService(BetRepository betRepository, UserRepository userRepository, EventRepository eventRepository, TransactionRepository transactionRepository) {
        super(betRepository);
        this.betRepository = betRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.transactionRepository = transactionRepository;
    }
    
    public Bet saveBet(BetDTO betDTO) {
        User userAuthenticated = AuthenticationUtil.getAuthenticatedUser().get();
        User user = userRepository.findById(userAuthenticated.getId()).get();
        Bet bet = new Bet();
        bet.setBet(betDTO.getBet());
        bet.setOdd(betDTO.getOdd());
        bet.setEvent(betDTO.getEvent());
        bet.setTeam(betDTO.getTeam());
        bet.setUser(user);
        user.setCoins(user.getCoins() - bet.getBet());
        transactionRepository.save(new Transaction((bet.getBet() * (-1)), new Date(System.currentTimeMillis()), user));
        userRepository.save(user);
        return betRepository.save(bet);
    }
    
    public List<Bet> getAllBetsByEvent(Long id) {
        Optional<Event> optionalEvent = this.eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            return betRepository.findBetsByEvent(event);
        } else {
            throw new ApiErrorException(ApiErrorCode.EVENT_NOT_FOUND);
        }
    }
    
    public List<HistoryDTO> getHistory() {
        Optional<User> optionalUserAuthenticated = AuthenticationUtil.getAuthenticatedUser();
        if (optionalUserAuthenticated.isPresent()) {
            List<HistoryDTO> historyDTOs = new ArrayList<>();
            User user = userRepository.findById(optionalUserAuthenticated.get().getId()).get();
            List<Bet> bets = betRepository.findBetByUser(user);
            bets.forEach(bet -> {
                Event event = eventRepository.findById(bet.getEvent().getId()).get();
                historyDTOs.add(new HistoryDTO(event, bet));
            });
            return historyDTOs;
        } else {
            throw new ApiErrorException(ApiErrorCode.UNAUTHORIZED);
        }
        
    }

    @Override
    public void executeBeforeSave(Bet entity) {
        
    }

    @Override
    public void executeAfterSave(Bet entity) {

    }

    @Override
    public void executeBeforeDelete(Bet entity) {

    }

    @Override
    public void executeAfterDelete(Bet entity) {

    }
}
