package com.bettinghouse.api.controller;

import com.bettinghouse.api.architecture.controller.CRUDController;
import com.bettinghouse.api.model.Sport;
import com.bettinghouse.api.service.SportService;
import com.bettinghouse.api.validator.SportValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sports")
public class SportController extends CRUDController<Sport> {
    
    private SportValidator sportValidator;
    private SportService sportService;
    
    @Autowired
    public SportController(SportValidator sportValidator, SportService sportService) {
        super(sportValidator, sportService);
        this.sportValidator = sportValidator;
        this.sportService = sportService;
    }
    
    @PostMapping("save")
    public ResponseEntity<Sport> saveSport(@RequestBody @Valid String name) {
        Sport sport = new Sport();
        sport.setName(name);
        sportValidator.validateBeforeSave(sport);
        Sport sportPersisted = sportService.save(sport);
        return ResponseEntity.ok(sportPersisted);
    }
}
