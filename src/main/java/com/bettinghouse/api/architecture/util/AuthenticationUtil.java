package com.bettinghouse.api.architecture.util;

import com.bettinghouse.api.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuthenticationUtil {

    public static Optional<User> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return Optional.of((User) authentication.getPrincipal());
        } else {
            return Optional.empty();
        }
    }
}