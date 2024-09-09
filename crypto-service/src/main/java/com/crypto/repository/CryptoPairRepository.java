package com.crypto.repository;

import com.crypto.entity.CryptoPair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CryptoPairRepository extends JpaRepository<CryptoPair, Long> {
    Optional<CryptoPair> findByCryptoPairName(String cryptoPairName);
}
