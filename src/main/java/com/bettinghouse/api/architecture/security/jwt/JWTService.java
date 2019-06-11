package com.bettinghouse.api.architecture.security.jwt;

import com.bettinghouse.api.architecture.security.TokenStatus;
import com.bettinghouse.api.model.User;

import java.util.Date;
import java.util.Optional;

public interface JWTService {
    
    String createToken(User user);
    String createToken(User user, int expiration);
    TokenStatus getStatus(String token, int refresh);
    TokenStatus identifyStatus(long current, long refresh, long expiration);
    Date getRefreshSchedule(long expiration, long expirationTimeDuration, long refreshTime);
    Optional<User> getUser(String token);
    String getExpirationDateISO(String token);
}
