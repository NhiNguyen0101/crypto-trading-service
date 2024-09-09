package com.crypto.service;

import com.crypto.common.exeptions.ResourceNotFoundException;
import com.crypto.dto.CryptoPriceDto;
import com.crypto.entity.CryptoPair;
import com.crypto.entity.CryptoPrice;
import com.crypto.entity.Tenant;
import com.crypto.repository.CryptoPairRepository;
import com.crypto.repository.CryptoPriceRepository;
import com.crypto.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CryptoPriceService {

    private final TenantRepository tenantRepository;

    private final CryptoPairRepository cryptoPairRepository;

    private final CryptoPriceRepository cryptoPriceRepository;


    public List<CryptoPriceDto> getAllCryptoPrices () {
        return cryptoPriceRepository.findAll().stream()
                .map(CryptoPrice::toDto)
                .collect(Collectors.toList());
    }

    public CryptoPrice save(CryptoPriceDto data) {
        Tenant tenant = tenantRepository.findByName(data.getTenant())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Tenant [%s] doesn't exist.", data.getTenant())));

        CryptoPair cryptoPair = cryptoPairRepository.findByCryptoPairName(data.getSymbol())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Crypto Pair [%s] doesn't exist.", data.getSymbol())));

        Optional<CryptoPrice> existingCryptoPrice =  cryptoPriceRepository.findByTenantIdAndCryptoPairId(tenant.getId(), cryptoPair.getId());

        if (existingCryptoPrice.isPresent()) {
            CryptoPrice existingObj = existingCryptoPrice.get();

            existingObj.setAskPrice(data.getAskPrice());
            existingObj.setAskQuantity(data.getAskQty());
            existingObj.setBidPrice(data.getBidPrice());
            existingObj.setBidQuantity(data.getBidQty());

            return cryptoPriceRepository.save(existingObj);
        }

        CryptoPrice cryptoPrice = CryptoPrice.builder()
                .bidPrice(data.getBidPrice())
                .bidQuantity(data.getBidQty())
                .askPrice(data.getAskPrice())
                .askQuantity(data.getAskQty())
                .tenantId(tenant.getId())
                .cryptoPairId(cryptoPair.getId())
                .build();

        return cryptoPriceRepository.save(cryptoPrice);
    }
}
