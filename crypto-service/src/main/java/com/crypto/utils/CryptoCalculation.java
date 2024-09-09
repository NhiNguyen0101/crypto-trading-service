package com.crypto.utils;

import com.crypto.dto.CryptoPriceDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static com.crypto.common.enums.TickerType.BTCUSDT;
import static com.crypto.common.enums.TickerType.ETHUSDT;

@UtilityClass
public class CryptoCalculation {

    public List<CryptoPriceDto> getBestSellPrice(List<CryptoPriceDto> binanceData, List<CryptoPriceDto> houbiData) {
        List<CryptoPriceDto> result = new ArrayList<>();

        // Best sell price for BTCUSDT
        CryptoPriceDto bestBinanceBTCUSDT = getMaxCryptoPriceByTicket(binanceData, BTCUSDT.name());
        CryptoPriceDto bestHoubiBTCUSDT = getMaxCryptoPriceByTicket(houbiData, BTCUSDT.name());

        result.add(bestSellPrice(bestBinanceBTCUSDT, bestHoubiBTCUSDT));

        // Best sell price for BTCUSDT
        CryptoPriceDto bestBinanceETHUSDT = getMaxCryptoPriceByTicket(binanceData, ETHUSDT.name());
        CryptoPriceDto bestHoubiPriceETHUSDT = getMaxCryptoPriceByTicket(houbiData, ETHUSDT.name());

        result.add(bestSellPrice(bestBinanceETHUSDT, bestHoubiPriceETHUSDT));

        return result;
    }

    public List<CryptoPriceDto> getBestBuyPrice(List<CryptoPriceDto> binanceData, List<CryptoPriceDto> houbiData) {
        List<CryptoPriceDto> result = new ArrayList<>();

        // Best buy price for BTCUSDT
        CryptoPriceDto bestBinanceBTCUSDT = getMinCryptoPriceByTicket(binanceData, BTCUSDT.name());
        CryptoPriceDto bestHoubiBTCUSDT = getMinCryptoPriceByTicket(houbiData, BTCUSDT.name());

        result.add(bestBuyPrice(bestBinanceBTCUSDT, bestHoubiBTCUSDT));

        // Best buy price for BTCUSDT
        CryptoPriceDto bestBinanceETHUSDT = getMinCryptoPriceByTicket(binanceData, ETHUSDT.name());
        CryptoPriceDto bestHoubiPriceETHUSDT = getMinCryptoPriceByTicket(houbiData, ETHUSDT.name());

        result.add(bestBuyPrice(bestBinanceETHUSDT, bestHoubiPriceETHUSDT));

        return result;
    }

    private CryptoPriceDto bestSellPrice(CryptoPriceDto bestBinance, CryptoPriceDto bestHoubiPrice) {
        boolean isBinanceBest = bestBinance.getBidPrice() > bestHoubiPrice.getBidPrice();

        return isBinanceBest ? bestBinance : bestHoubiPrice;
    }

    private CryptoPriceDto bestBuyPrice(CryptoPriceDto bestBinance, CryptoPriceDto bestHoubiPrice) {
        boolean isBinanceBest = bestBinance.getAskPrice() < bestHoubiPrice.getAskPrice();

        return isBinanceBest ? bestBinance : bestHoubiPrice;
    }

    private CryptoPriceDto getMaxCryptoPriceByTicket(List<CryptoPriceDto> data, String tickerType) {
        return data.stream()
                .filter(r -> tickerType.equalsIgnoreCase(r.getSymbol()))
                .max(Comparator.comparing(CryptoPriceDto::getBidPrice))
                .orElseThrow(NoSuchElementException::new);
    }

    private CryptoPriceDto getMinCryptoPriceByTicket(List<CryptoPriceDto> data, String tickerType) {
        return data.stream()
                .filter(r -> tickerType.equalsIgnoreCase(r.getSymbol()))
                .min(Comparator.comparing(CryptoPriceDto::getAskPrice))
                .orElseThrow(NoSuchElementException::new);
    }
}
