package com.bettinghouse.api.model;

import com.bettinghouse.api.architecture.model.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "user")
@SequenceGenerator(name = "default_generator", sequenceName = "user_id_seq", allocationSize = 1)
public class User extends AbstractEntity {

    @NotBlank(message = "NAME_IS_NULL")
    private String name;

    @NotBlank(message = "EMAIL_IS_NULL")
    private String email;

    @NotBlank(message = "LOGIN_IS_NULL")
    private String login;

    @NotBlank(message = "PASSWORD_IS_NULL")
    private String password;

    @Transient
    private String confirmPassword;

    @Transient
    private String token;

    private double coins;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id", foreignKey = @ForeignKey(name = "fk_user_profile_id"), nullable = false)
    private Profile profile;

    public User() {
    }

    public User(@NotBlank String name, @NotBlank String email, @NotBlank String login) {
        this.name = name;
        this.email = email;
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equal(name, user.name) &&
                Objects.equal(email, user.email) &&
                Objects.equal(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), name, email, login);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}