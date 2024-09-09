package com.crypto.service;

import com.crypto.client.dataGateway.DataGateway;
import com.crypto.dto.CryptoPriceDto;
import com.crypto.utils.CryptoCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CryptoService {

    @Autowired
    private DataGateway dataGateway;

    @Autowired
    private CryptoPriceService cryptoPriceService;



    public List<CryptoPriceDto> getLatestBestAggregatedPrice() {

        List<CryptoPriceDto> binanceDataSet = dataGateway.getBinanceAllPrices();
        List<CryptoPriceDto> houiDataSet = dataGateway.getHoubiAllPrices();

        List<CryptoPriceDto> bestPrices =  new ArrayList<>();
        List<CryptoPriceDto> bestSellPrices = CryptoCalculation.getBestSellPrice(binanceDataSet, houiDataSet);
        List<CryptoPriceDto> bestBuyPrices = CryptoCalculation.getBestBuyPrice(binanceDataSet, houiDataSet);

        bestPrices.addAll(bestSellPrices);
        bestPrices.addAll(bestBuyPrices);

        return bestBuyPrices;
    }

    @Scheduled(fixedRate = 10000L)
    public void saveLatestPricesInterval() {
        List<CryptoPriceDto> bestPrice = getLatestBestAggregatedPrice();

        bestPrice.stream()
                .filter(Objects::nonNull)
                .forEach(obj -> cryptoPriceService.save(obj));

    }
}
