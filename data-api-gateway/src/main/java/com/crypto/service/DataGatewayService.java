package com.crypto.service;

import com.crypto.client.dataGateway.binanceApiGateway.BinanceApiGateway;
import com.crypto.client.dataGateway.houbiApiGateway.HoubiApiGateway;
import com.crypto.client.dto.PriceDataResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class DataGatewayService {

    private final BinanceApiGateway binanceApiGateway;
    private final HoubiApiGateway houbiApiGateway;

    @SneakyThrows
    public PriceDataResponse getBinanceBitcoinEthereumPrices() {

        return PriceDataResponse.builder()
                .priceDataSet(binanceApiGateway.getBitcoinEthereumPrices())
                .build();
    }

    @SneakyThrows
    public PriceDataResponse getHoubiBitcoinEthereumPrices() {

        return PriceDataResponse.builder()
                .priceDataSet(houbiApiGateway.getBitcoinEthereumPrices())
                .build();
    }

}
