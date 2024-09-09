package com.crypto.client.dataGateway.houbiApiGateway;

import com.crypto.client.dataGateway.houbiApiGateway.dto.HoubiPriceDataResponse;
import com.crypto.common.config.feign.FeignExceptionDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "houbi-base-url",
        url = "${houbi-api-gateway.base-url}",
        path = "${houbi-api-gateway.path}",
        configuration = {
                HoubiApiGatewayFeignConfiguration.class,
                FeignExceptionDecoder.class
        }
)
public interface HoubiApiGatewayFeignClient {

    @GetMapping
    HoubiPriceDataResponse getAllPrices();
}

