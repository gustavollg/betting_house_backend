package com.bettinghouse.api.repository;

import com.bettinghouse.api.model.Event;
import com.bettinghouse.api.model.Odd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OddRepository extends JpaRepository<Odd, Long> {
    
    List<Odd> findAllByEvent(Event event);
}
