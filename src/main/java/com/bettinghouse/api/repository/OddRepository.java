package com.bettinghouse.api.repository;

import com.bettinghouse.api.model.Odd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OddRepository extends JpaRepository<Odd, Long> {
}
