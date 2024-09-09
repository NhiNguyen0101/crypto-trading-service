package com.crypto.controller;

import com.crypto.client.dto.PriceDataResponse;
import com.crypto.common.pojo.BaseObjectResult;
import com.crypto.common.pojo.ResultBuilder;
import com.crypto.service.DataGatewayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Data API Gateway")
@RestController
@RequestMapping("/${api.version}/data-gateway")
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class DataGatewayController {

    private final DataGatewayService dataGatewayService;

    @GetMapping(path = "/binance/all-prices")
    @Operation(summary = "Get all prices of Ethereum and Bitcoin from binance")
    public BaseObjectResult<PriceDataResponse> getBinanceBitcoinEthereumPrices() {

        PriceDataResponse result = dataGatewayService.getBinanceBitcoinEthereumPrices();

        return ResultBuilder.newResult()
                .success()
                .build(result);
    }

    @GetMapping(path = "/houbi/all-prices")
    @Operation(summary = "Get all prices of Ethereum and Bitcoin from houbi")
    public BaseObjectResult<PriceDataResponse> getHoubiBitcoinEthereumPrices() {

        PriceDataResponse result = dataGatewayService.getHoubiBitcoinEthereumPrices();

        return ResultBuilder.newResult()
                .success()
                .build(result);
    }

}