package com.crypto.client.dataGateway.houbiApiGateway;

import com.crypto.common.config.feign.FeignClientConfiguration;
import feign.Client;
import feign.Logger;
import org.springframework.context.annotation.Bean;

import java.security.GeneralSecurityException;

public class HoubiApiGatewayFeignConfiguration {

    private final FeignClientConfiguration feignClientConfig = new FeignClientConfiguration();

    @Bean
    public Client feignClient() throws GeneralSecurityException {
        return feignClientConfig.noVerificationFeignClient();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
