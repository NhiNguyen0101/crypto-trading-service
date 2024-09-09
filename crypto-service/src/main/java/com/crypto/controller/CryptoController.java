package com.crypto.controller;

import com.crypto.common.pojo.BaseResult;
import com.crypto.common.pojo.ResultBuilder;
import com.crypto.service.CryptoPriceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/${api.version}")
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class CryptoController {

    private final CryptoPriceService cryptoPriceService;

    @GetMapping(path = "/best-prices")
    @Operation(summary = "Get the latest best aggregated ETHUSDT, BTCUSDT prices from Binance and Houbi")
    public BaseResult getLatestBestAggregatedPrice() {

        return ResultBuilder.newResult().build(cryptoPriceService.getAllCryptoPrices() );
    }
}
