package com.crypto.client.dataGateway.binanceApiGateway;

import com.crypto.client.dto.PriceData;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.crypto.common.enums.TickerType.BTCUSDT;
import static com.crypto.common.enums.TickerType.ETHUSDT;

@Component
public class BinanceApiGateway {

    @Autowired
    private BinanceApiGatewayFeignClient feignClient;

    @SneakyThrows
    public List<PriceData> getBitcoinEthereumPrices() {
        List<PriceData> dataList = feignClient.getAllPrices();

        if (CollectionUtils.isEmpty(dataList)) {
            return Collections.emptyList();
        }

        return dataList.stream()
                .filter(record -> Objects.nonNull(record) && Objects.nonNull(record.getSymbol()))
                .filter(record -> {
                    String symbol = record.getSymbol();
                    return ETHUSDT.name().equalsIgnoreCase(symbol) ||
                            BTCUSDT.name().equalsIgnoreCase(symbol);
                })
                .collect(Collectors.toList());
    }
}
