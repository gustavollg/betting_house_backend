package com.bettinghouse.api.controller;

import com.bettinghouse.api.architecture.controller.CRUDController;
import com.bettinghouse.api.model.User;
import com.bettinghouse.api.service.UserService;
import com.bettinghouse.api.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
