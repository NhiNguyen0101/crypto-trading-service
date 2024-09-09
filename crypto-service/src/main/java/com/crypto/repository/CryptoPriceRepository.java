package com.crypto.repository;

import com.crypto.entity.CryptoPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CryptoPriceRepository extends JpaRepository<CryptoPrice, Long> {
    Optional<CryptoPrice> findByTenantIdAndCryptoPairId(Long tenantId, Long cryptoPairId);
}
