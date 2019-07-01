package com.bettinghouse.api.model;

import com.bettinghouse.api.architecture.model.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "transaction")
@SequenceGenerator(name = "default_generator", sequenceName = "transaction_id_seq", allocationSize = 1)
public class Transaction extends AbstractEntity {

    @NotNull(message = "COINS_IS_NULL")
    private double coins;

    private Date startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_transaction_user_id"), nullable = false)
    @NotNull(message = "USER_IS_NULL")
    private User user;

    public Transaction() {
    }

    public Transaction(@NotNull(message = "COINS_IS_NULL") double coins, @NotNull(message = "START_DATE_IS_NULL") Date startDate,
                       @NotNull(message = "USER_IS_NULL") User user) {
        this.coins = coins;
        this.startDate = startDate;
        this.user = user;
    }

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
