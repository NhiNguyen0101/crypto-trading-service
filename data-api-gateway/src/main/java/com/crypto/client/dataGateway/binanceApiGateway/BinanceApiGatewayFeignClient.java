package com.crypto.client.dataGateway.binanceApiGateway;

import com.crypto.client.dto.PriceData;
import com.crypto.common.config.feign.FeignExceptionDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "binance-base-url",
        url = "${binance-api-gateway.base-url}",
        path = "${binance-api-gateway.path}",
        configuration = {
                BinanceApiGatewayFeignConfiguration.class,
                FeignExceptionDecoder.class
        }
)
public interface BinanceApiGatewayFeignClient {

    @GetMapping
    List<PriceData> getAllPrices();
}

