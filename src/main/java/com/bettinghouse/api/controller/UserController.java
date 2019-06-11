package com.bettinghouse.api.controller;

import com.bettinghouse.api.architecture.controller.CRUDController;
import com.bettinghouse.api.model.User;
import com.bettinghouse.api.service.UserService;
import com.bettinghouse.api.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
