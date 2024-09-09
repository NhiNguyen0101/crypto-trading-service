package com.crypto.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum TickerType {
    BTCUSDT, ETHUSDT;

    private static final Map<String, TickerType> CACHE;

    static {
        CACHE = new HashMap<>();
        CACHE.put(BTCUSDT.name(), BTCUSDT);
        CACHE.put(ETHUSDT.name(), ETHUSDT);
    }

    public static boolean hasTickerSymbol(String symbol) {
        return CACHE.containsKey(symbol);
    }

    public static TickerType getEnum(String symbol) {
        return CACHE.get(symbol);
    }
}
