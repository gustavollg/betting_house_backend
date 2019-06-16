package com.bettinghouse.api.validator.helper;

import com.bettinghouse.api.controller.dto.EventDTO;
import com.bettinghouse.api.model.Team;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventValidatorHelper {

    public boolean teamDoesntBelongToSport(EventDTO eventDTO) {
        List<Team> teams = new ArrayList<>();
        eventDTO.getOddDTOs().forEach(oddDTO -> {
            if (!oddDTO.getTeam().getSport().getId().equals(eventDTO.getSport().getId())) {
                teams.add(oddDTO.getTeam());
            }
        });
        return teams.size() > 0;
    }
}
