package com.example.cardsmicroservice.repository;

import com.example.cardsmicroservice.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Cards, Long> {
    Optional<Cards> findByMobileNumber(String mobileNumber);
    Optional<Cards> findByCardNumber(String cardNumber);
}
