package com.bettinghouse.api.model;

import com.bettinghouse.api.architecture.model.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity(name = "profile")
@SequenceGenerator(name = "default_generator", sequenceName = "profile_id_seq", allocationSize = 1)
public class Profile extends AbstractEntity {

    @NotBlank(message = "NAME_IS_NULL")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "profile_role",
            joinColumns = {@JoinColumn(name = "profile_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_profile_role_profile_id"))},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_profile_role_role_id"))}
    )
    private Set<Role> roles;

    public Profile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}