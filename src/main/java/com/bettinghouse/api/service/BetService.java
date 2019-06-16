package com.bettinghouse.api.service;

import com.bettinghouse.api.architecture.service.CRUDService;
import com.bettinghouse.api.architecture.util.AuthenticationUtil;
import com.bettinghouse.api.controller.dto.BetDTO;
import com.bettinghouse.api.model.Bet;
import com.bettinghouse.api.model.User;
import com.bettinghouse.api.repository.BetRepository;
import com.bettinghouse.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetService extends CRUDService<Bet> {
    
    private BetRepository betRepository;
    private UserRepository userRepository;

    @Autowired
    public BetService(BetRepository betRepository, UserRepository userRepository) {
        super(betRepository);
        this.betRepository = betRepository;
        this.userRepository = userRepository;
    }
    
    public Bet saveBet(BetDTO betDTO) {
        User userAuthenticated = AuthenticationUtil.getAuthenticatedUser().get();
        User user = userRepository.findById(userAuthenticated.getId()).get();
        Bet bet = new Bet();
        bet.setBet(betDTO.getBet());
        bet.setOdd(betDTO.getOdd());
        bet.setEvent(betDTO.getEvent());
        bet.setUser(user);
        user.setCoins(user.getCoins() - bet.getBet());
        userRepository.save(user);
        return betRepository.save(bet);
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
