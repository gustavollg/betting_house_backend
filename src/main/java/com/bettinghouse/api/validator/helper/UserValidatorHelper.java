package com.bettinghouse.api.validator.helper;

import com.bettinghouse.api.controller.dto.LoginParametersDTO;
import com.bettinghouse.api.model.User;
import com.bettinghouse.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserValidatorHelper {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserValidatorHelper(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isInformedPasswordsEqual(User user) {
        return user.getPassword().equals(user.getConfirmPassword());
    }

    public boolean isLoginAlreadyInformed(User user) {
        Optional<User> optionalUser = userRepository.findByLogin(user.getLogin());
        return optionalUser.isPresent();
    }

    public boolean isEmailAlreadyInformed(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        return optionalUser.isPresent();
    }

    public boolean isUserCredentialsCorrect(LoginParametersDTO loginParametersDTO) {
        Optional<User> optionalUser = userRepository.findByLogin(loginParametersDTO.getLogin());
        return optionalUser.isPresent() && passwordEncoder.matches(loginParametersDTO.getPassword(), optionalUser.get().getPassword());
    }
}
