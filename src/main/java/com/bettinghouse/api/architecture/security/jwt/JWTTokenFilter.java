package com.bettinghouse.api.architecture.security.jwt;

import com.bettinghouse.api.architecture.security.Origin;
import com.bettinghouse.api.architecture.security.TokenStatus;
import com.bettinghouse.api.architecture.security.identification.AuthorityManager;
import com.bettinghouse.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class JWTTokenFilter extends OncePerRequestFilter {
    
    private String HEADER_AUTHORIZATION = "Authorization";
    private String HEADER_ORIGIN = "X-Origin";
    
    @Autowired
    private JWTService jwtService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> headerToken = getTokenString(httpServletRequest.getHeader(HEADER_AUTHORIZATION));
        if (headerToken.isPresent()) {
            String token = headerToken.get();
            String headerOrigin = httpServletRequest.getHeader(HEADER_ORIGIN);
            int refreshTime = getRefreshTime(headerOrigin);
            
            jwtService.getUser(token).ifPresent(user -> {
                if (isThereAnAuthenticationContext()) {
                    if (jwtService.getStatus(token, refreshTime) == TokenStatus.VALID) {
                        setAuthentication(user, httpServletRequest);
                        httpServletResponse.setHeader(HEADER_AUTHORIZATION, token);
                    } else if (jwtService.getStatus(token, refreshTime) ==  TokenStatus.REFRESH) {
                        String newToken = jwtService.createToken(user);
                        setAuthentication(user, httpServletRequest);
                        httpServletResponse.setHeader(HEADER_AUTHORIZATION, newToken);
                    } else if (jwtService.getStatus(token, refreshTime) == TokenStatus.EXPIRED) {
                        setAuthentication(null, httpServletRequest);
                    }
                }
            });
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
    
    private int getRefreshTime(String headerOrigin) {
        return Origin.BETTING_HOUSE.getRefreshTime();
    }
    
    private boolean isThereAnAuthenticationContext() {
        return SecurityContextHolder.getContext().getAuthentication() == null;
    }
    
    private void setAuthentication(User user, HttpServletRequest request) {
        if (user != null) {
            Collection<GrantedAuthority> authorities = AuthorityManager.getAuthorities(user);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
            WebAuthenticationDetailsSource webAuthenticationDetailsSource = new WebAuthenticationDetailsSource();
            authenticationToken.setDetails(webAuthenticationDetailsSource.buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }
    
    private Optional<String> getTokenString(String header) {
        if (header != null) {
            List<String> headerSplitted = Arrays.asList(header.split("\\s+"));
            if (headerSplitted.size() < 2) {
                return Optional.empty();
            } else {
                return Optional.ofNullable(headerSplitted.get(1));
            }
        } else {
            return Optional.empty();
        }
        
    }
}
