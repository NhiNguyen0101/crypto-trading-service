package com.crypto.client.config;

import com.crypto.common.config.feign.FeignClientConfiguration;
import feign.Client;
import org.springframework.context.annotation.Bean;

import java.security.GeneralSecurityException;

public class FeignConfiguration {

    private final FeignClientConfiguration feignClientConfig = new FeignClientConfiguration();

    @Bean
    public Client feignClient() throws GeneralSecurityException {
        return feignClientConfig.noVerificationFeignClient();
    }
}
