package com.crypto.common.config.feign.models;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@RequiredArgsConstructor
public enum ServiceType {
    DATA_GATEWAY(Pattern.compile("^/?data-gateway-service/.+")),
    NOT_MAPPED(null);

    private final Pattern urlPattern;

    @NonNull
    public static ServiceType findFromRequestPath(String path) {
        return Stream.of(ServiceType.values())
                .filter(value -> value.matches(path))
                .findFirst()
                .orElse(NOT_MAPPED);
    }

    public boolean matches(String urlPath) {
        return Optional.ofNullable(urlPattern)
                .map(i -> i.matcher(urlPath).matches())
                .orElse(false);
    }
}
