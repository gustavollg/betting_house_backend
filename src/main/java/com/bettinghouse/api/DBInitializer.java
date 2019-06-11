package com.bettinghouse.api;

import com.bettinghouse.api.model.Profile;
import com.bettinghouse.api.model.User;
import com.bettinghouse.api.model.util.EnumProfile;
import com.bettinghouse.api.repository.ProfileRepository;
import com.bettinghouse.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ConditionalOnProperty(name = "app.db_init", havingValue = "true")
public class DBInitializer implements CommandLineRunner {
    
    private UserRepository userRepository;
    private ProfileRepository profileRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DBInitializer(UserRepository userRepository, ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public void run(String... args) {
        if (!userRepository.findByLogin("admin").isPresent()) {
            Optional<Profile> optionalProfile = profileRepository.findByName(EnumProfile.ADMINISTRATOR.name());
            User user = new User("Administrator", "admin@email.com", "admin");
            user.setPassword(passwordEncoder.encode("123"));
            optionalProfile.ifPresent(user::setProfile);
            userRepository.save(user);
        }
    }
}
