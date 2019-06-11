package com.bettinghouse.api.controller;

import com.bettinghouse.api.architecture.security.jwt.JWTService;
import com.bettinghouse.api.controller.dto.LoginParametersDTO;
import com.bettinghouse.api.model.User;
import com.bettinghouse.api.repository.UserRepository;
import com.bettinghouse.api.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {
    
    private UserRepository userRepository;
    private LoginValidator loginValidator;
    private JWTService jwtService;

    @Autowired
    public LoginController(UserRepository userRepository, LoginValidator loginValidator, JWTService jwtService) {
        this.userRepository = userRepository;
        this.loginValidator = loginValidator;
        this.jwtService = jwtService;
    }

    @InitBinder("loginParametersDTO")
    public void initLoginValidationBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(loginValidator);
    }

    @PostMapping("/")
    public ResponseEntity<User> login(@RequestBody @Valid LoginParametersDTO loginParametersDTO) {
        Optional<User> optionalUser = userRepository.findByLogin(loginParametersDTO.getLogin());
        User user = optionalUser.get();
        user.setToken(jwtService.createToken(user));
        return ResponseEntity.ok(user);
    }
}
