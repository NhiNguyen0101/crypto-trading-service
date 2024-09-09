package com.crypto.client.dataGateway;

import com.crypto.client.config.FeignConfiguration;
import com.crypto.common.config.feign.FeignExceptionDecoder;
import com.crypto.client.dto.PriceDataResponse;
import com.crypto.common.pojo.BaseObjectResult;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "data-gateway",
        configuration = {FeignConfiguration.class, FeignExceptionDecoder.class},
        url = "${data-gateway.base-url}",
        path = "/${api.version}/data-gateway")
public interface DataGatewayFeignClient {

    @GetMapping(path = "/binance/all-prices")
    @Operation(summary = "Get all prices of Ethereum and Bitcoin from binance")
    BaseObjectResult<PriceDataResponse> getBinanceBitcoinEthereumPrices();

    @GetMapping(path = "/houbi/all-prices")
    @Operation(summary = "Get all prices of Ethereum and Bitcoin from houbi")
    BaseObjectResult<PriceDataResponse> getHoubiBitcoinEthereumPrices();
}
