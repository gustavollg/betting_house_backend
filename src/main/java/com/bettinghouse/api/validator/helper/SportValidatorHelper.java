package com.bettinghouse.api.validator.helper;

import com.bettinghouse.api.model.Sport;
import com.bettinghouse.api.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SportValidatorHelper {

    private SportRepository sportRepository;

    @Autowired
    public SportValidatorHelper(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    public boolean isSportPresentOnDatabase(Long id) {
        Optional<Sport> optionalSport = sportRepository.findById(id);
        return optionalSport.isPresent();
    }
    
    public boolean doesSportNameAlreadyExist(Sport sport) {
        List<Sport> sports = sportRepository.findAllByName(sport.getName());
        return sports.size() > 0;
    }
}
