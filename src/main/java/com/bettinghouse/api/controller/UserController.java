package com.bettinghouse.api.controller;

import com.bettinghouse.api.architecture.controller.CRUDController;
import com.bettinghouse.api.model.Transaction;
import com.bettinghouse.api.model.User;
import com.bettinghouse.api.service.UserService;
import com.bettinghouse.api.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends CRUDController<User> {

    private UserValidator userValidator;
    private UserService userService;
    
    @Autowired
    public UserController(UserValidator userValidator, UserService userService) {
        super(userValidator, userService);
        this.userValidator = userValidator;
        this.userService = userService;
    }
    
    @PostMapping("add-coins")
    public ResponseEntity<User> addCoins(@RequestBody @Valid Long coins) {
        userValidator.validateUserBeforeAddCoins(coins);
        User user = userService.addCoins(coins);
        userService.addTransaction(coins, user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("become-vip")
    public ResponseEntity<User> becomeVIP() {
        userValidator.validateUserBeforeBecomingVIP();
        User user = userService.becomeVIP();
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("transactions")
    public ResponseEntity<List<Transaction>> getTransactions() {
        List<Transaction> transactions = userService.getTransactions();
        return ResponseEntity.ok(transactions);
    }
}
