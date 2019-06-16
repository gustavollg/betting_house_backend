package com.bettinghouse.api.repository;

import com.bettinghouse.api.model.Sport;
import com.bettinghouse.api.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    
    List<Team> findAllBySport(Sport sport);
}
