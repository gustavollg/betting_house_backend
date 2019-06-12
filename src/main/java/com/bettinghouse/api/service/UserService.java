package com.bettinghouse.api.service;

import com.bettinghouse.api.architecture.exception.ApiErrorException;
import com.bettinghouse.api.architecture.service.CRUDService;
import com.bettinghouse.api.architecture.validator.dto.ApiErrorCode;
import com.bettinghouse.api.model.Profile;
import com.bettinghouse.api.model.User;
import com.bettinghouse.api.model.util.EnumProfile;
import com.bettinghouse.api.repository.ProfileRepository;
import com.bettinghouse.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends CRUDService<User> {

    private UserRepository userRepository;
    private ProfileRepository profileRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void addRegularProfileToUser(User user) {
        Optional<Profile> adminProfile = profileRepository.findByName(EnumProfile.REGULAR.name());
        if (adminProfile.isPresent()) {
            user.setProfile(adminProfile.get());
        } else {
            throw new ApiErrorException(ApiErrorCode.PROFILE_NOT_FOUND);
        }
    }

    @Override
    public void executeBeforeSave(User user) {
        addRegularProfileToUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    public void executeAfterSave(User user) {

    }

    @Override
    public void executeBeforeDelete(User entity) {

    }

    @Override
    public void executeAfterDelete(User entity) {

    }
}