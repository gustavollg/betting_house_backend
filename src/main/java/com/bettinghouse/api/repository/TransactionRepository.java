package com.bettinghouse.api.repository;

import com.bettinghouse.api.model.Transaction;
import com.bettinghouse.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    List<Transaction> findAllByUser(User user);
}
