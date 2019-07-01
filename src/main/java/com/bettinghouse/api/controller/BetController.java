package com.bettinghouse.api.controller;

import com.bettinghouse.api.architecture.controller.CRUDController;
import com.bettinghouse.api.controller.dto.BetDTO;
import com.bettinghouse.api.controller.dto.HistoryDTO;
import com.bettinghouse.api.model.Bet;
import com.bettinghouse.api.service.BetService;
import com.bettinghouse.api.validator.BetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    
    @PostMapping("save")
    public ResponseEntity<Bet> saveBet(@RequestBody @Valid BetDTO betDTO) {
        Bet bet = betService.saveBet(betDTO);
        return ResponseEntity.ok(bet);
    }
    
    @GetMapping("find-bets-by-events/{id}")
    public ResponseEntity<List<Bet>> getAllBetsByEvent(@PathVariable Long id) {
        List<Bet> bets = betService.getAllBetsByEvent(id);
        return ResponseEntity.ok(bets);
    }

    @GetMapping("get-bet-history")
    public ResponseEntity<List<HistoryDTO>> getBetHistory() {
        List<HistoryDTO> historyDTOs = betService.getHistory();
        return ResponseEntity.ok(historyDTOs);
    }

}
