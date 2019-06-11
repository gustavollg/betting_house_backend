package com.bettinghouse.api.architecture.security.identification;

import com.bettinghouse.api.model.Profile;
import com.bettinghouse.api.model.Role;
import com.bettinghouse.api.model.User;
import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;

public class AuthorityManager {
    
    public static Collection<GrantedAuthority> getAuthorities(User user) {
        Collection<GrantedAuthority> authorities = Sets.newHashSet();
        Profile profile = user.getProfile();
        if (profile != null) {
            Set<Role> roles = profile.getRoles();
            if (roles != null) {
                for (Role role : roles) {
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                }
            }
        }
        return authorities;
    }
}
