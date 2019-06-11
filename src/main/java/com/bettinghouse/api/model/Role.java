package com.bettinghouse.api.model;

import com.bettinghouse.api.architecture.model.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity(name = "role")
@SequenceGenerator(name = "default_generator", sequenceName = "role_id_seq", allocationSize = 1)
public class Role extends AbstractEntity implements GrantedAuthority {

    @NotBlank(message = "NAME_IS_NULL")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Profile> profiles;

    public Role() {
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return name;
    }

    public String getName() {
        return name;
    }
}
