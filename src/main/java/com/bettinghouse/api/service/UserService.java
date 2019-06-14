package com.bettinghouse.api.service;

import com.bettinghouse.api.architecture.exception.ApiErrorException;
import com.bettinghouse.api.architecture.service.CRUDService;
import com.bettinghouse.api.architecture.util.AuthenticationUtil;
import com.bettinghouse.api.architecture.validator.dto.ApiErrorCode;
import com.bettinghouse.api.model.Profile;
import com.bettinghouse.api.model.Transaction;
import com.bettinghouse.api.model.User;
import com.bettinghouse.api.model.util.EnumProfile;
import com.bettinghouse.api.repository.ProfileRepository;
import com.bettinghouse.api.repository.TransactionRepository;
import com.bettinghouse.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends CRUDService<User> {

    private UserRepository userRepository;
    private ProfileRepository profileRepository;
    private TransactionRepository transactionRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, ProfileRepository profileRepository, PasswordEncoder passwordEncoder, TransactionRepository transactionRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.transactionRepository = transactionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void addRegularProfileToUser(User user) {
        Optional<Profile> regularProfile = profileRepository.findByName(EnumProfile.REGULAR.name());
        if (regularProfile.isPresent()) {
            user.setProfile(regularProfile.get());
        } else {
            throw new ApiErrorException(ApiErrorCode.PROFILE_NOT_FOUND);
        }
    }
    
    public User addCoins(double coins) {
        Optional<User> optionalUser = AuthenticationUtil.getAuthenticatedUser();
        if (optionalUser.isPresent()) {
            User user = userRepository.findById(optionalUser.get().getId()).get();
            user.setCoins(user.getCoins() + coins);
            userRepository.save(user);
            return user;
        } else {
            throw new ApiErrorException(ApiErrorCode.UNAUTHORIZED);
        }
    }
    
    public User becomeVIP() {
        Optional<User> optionalUser = AuthenticationUtil.getAuthenticatedUser();
        Optional<Profile> optionalVIPProfile = profileRepository.findByName(EnumProfile.VIP.name());
        if (optionalUser.isPresent() && optionalVIPProfile.isPresent()) {
            User user = userRepository.findById(optionalUser.get().getId()).get();
            if (user.getCoins() < 50) {
                throw new ApiErrorException(ApiErrorCode.NOT_ENOUGH_COINS);
            } else {
                user.setProfile(optionalVIPProfile.get());
                user.setCoins(user.getCoins() - 50);
                userRepository.save(user);
                return user;
            }
        } else {
            throw new ApiErrorException(ApiErrorCode.UNAUTHORIZED);
        }
    }
    
    public void addTransaction(double coins, User user) {
        Transaction transaction = new Transaction(coins, user);
        transactionRepository.save(transaction);
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