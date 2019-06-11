package com.bettinghouse.api.architecture.security.jwt;

import com.bettinghouse.api.architecture.security.TokenStatus;
import com.bettinghouse.api.architecture.util.DateUtil;
import com.bettinghouse.api.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DefaultJWTConfig implements JWTService {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Value("jwt.secret")
    private String secret;
    
//    @Value("jwt.expiration_time")
    private Integer expirationTimeDuration = 120;
    
    private int MILLISECONDS_IN_ONE_MINUTE = 60000;
    
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public String createToken(User user) {
        return createToken(user, expirationTimeDuration);
    }

    @Override
    public String createToken(User user, int expiration) {
        String userJson = "";
        try {
            userJson = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            logger.error("An error has occured when trying to convert the user "
                    + user.getLogin() + " to JSON");
            e.printStackTrace();
        }
        
        return Jwts.builder()
                .setSubject(userJson)
                .setExpiration(getTimeToExpire(expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    @Override
    public TokenStatus getStatus(String token, int refresh) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

        long current = new Date(System.currentTimeMillis()).getTime();
        long expiration = claimsJws.getBody().getExpiration().getTime();
        long refreshSchedule = getRefreshSchedule(expiration, expirationTimeDuration, refresh).getTime();

        return identifyStatus(current, refreshSchedule, expiration);
    }

    @Override
    public TokenStatus identifyStatus(long current, long refresh, long expiration) {
        if (refresh >= current) {
            return TokenStatus.VALID;
        } else if (current >= (refresh + 1) && current <= (expiration - 1)) {
            return TokenStatus.REFRESH;
        } else {
            return TokenStatus.EXPIRED;
        }
    }

    @Override
    public Date getRefreshSchedule(long expiration, long expirationTimeDuration, long refreshTime) {
        return new Date(expiration + (refreshTime - expirationTimeDuration) * MILLISECONDS_IN_ONE_MINUTE);
    }

    @Override
    public Optional<User> getUser(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return Optional.of(objectMapper.readValue(claimsJws.getBody().getSubject(), User.class));
        } catch (Exception e) {
            logger.error("An error has occured when trying to convert the token to a user", e);
            return Optional.empty();
        }
    }

    @Override
    public String getExpirationDateISO(String token) {
        Date expiration = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
        return DateUtil.toISO8601UTC(expiration);
    }
    
    private Date getTimeToExpire(int minutes) {
        return new Date(System.currentTimeMillis() + minutes * MILLISECONDS_IN_ONE_MINUTE);
    }
}
