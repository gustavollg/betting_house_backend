package com.bettinghouse.api.controller;

import com.bettinghouse.api.architecture.controller.CRUDController;
import com.bettinghouse.api.model.Bet;
import com.bettinghouse.api.service.BetService;
import com.bettinghouse.api.validator.BetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bets")
public class BetController extends CRUDController<Bet> {
    
    private BetValidator betValidator;
    private BetService betService;

    @Autowired
    public BetController(BetValidator betValidator, BetService betService) {
        super(betValidator, betService);
        this.betValidator = betValidator;
        this.betService = betService;
    }
}
