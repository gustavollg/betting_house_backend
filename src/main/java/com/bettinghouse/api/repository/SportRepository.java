package com.bettinghouse.api.repository;

import com.bettinghouse.api.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {
    
    List<Sport> findAllByName(String name);
}
