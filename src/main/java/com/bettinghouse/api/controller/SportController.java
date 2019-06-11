package com.bettinghouse.api.controller;

import com.bettinghouse.api.architecture.controller.CRUDController;
import com.bettinghouse.api.model.Sport;
import com.bettinghouse.api.service.SportService;
import com.bettinghouse.api.validator.SportValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
