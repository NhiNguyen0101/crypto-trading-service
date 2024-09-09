package com.crypto.common.config.feign;

import feign.Client;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLSocketFactory;
import java.security.GeneralSecurityException;

public class FeignClientConfiguration {

    /**
     * This feign configuration forwards inbound headers to callers, although it doesn't perform HTTPS verification.
     */
    public Client noVerificationFeignClient() throws GeneralSecurityException {
        SSLSocketFactory sslContextFactory = SSLContexts.custom()
                .loadTrustMaterial(null, (chain, authType) -> true)
                .build()
                .getSocketFactory();
        return new Client.Default(sslContextFactory, new NoopHostnameVerifier());
    }


}
