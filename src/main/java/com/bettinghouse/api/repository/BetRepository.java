package com.bettinghouse.api.repository;

import com.bettinghouse.api.model.Bet;
import com.bettinghouse.api.model.Event;
import com.bettinghouse.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
    
    List<Bet> findBetsByEvent(Event event);
    
    List<Bet> findBetByUser(User user);
}
