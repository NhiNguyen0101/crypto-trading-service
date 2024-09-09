package com.crypto.client.dataGateway.houbiApiGateway;

import com.crypto.client.dataGateway.houbiApiGateway.dto.HoubiPriceData;
import com.crypto.client.dataGateway.houbiApiGateway.dto.HoubiPriceDataResponse;
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
public class HoubiApiGateway {

    @Autowired
    private HoubiApiGatewayFeignClient feignClient;

    @SneakyThrows
    public List<PriceData> getBitcoinEthereumPrices() {
        HoubiPriceDataResponse data = feignClient.getAllPrices();

        if (data == null || CollectionUtils.isEmpty(data.getPriceDataSet())) {
            return Collections.emptyList();
        }

        List<HoubiPriceData> dataList = data.getPriceDataSet();

        return dataList.stream()
                .filter(record -> Objects.nonNull(record) && Objects.nonNull(record.getSymbol()))
                .filter(record -> {
                    String symbol = record.getSymbol();
                    return ETHUSDT.name().equalsIgnoreCase(symbol) ||
                            BTCUSDT.name().equalsIgnoreCase(symbol);
                })
                .map(record -> PriceData.builder()
                        .symbol(record.getSymbol())
                        .askPrice(record.getAsk())
                        .askQty(record.getAskSize())
                        .bidPrice(record.getBid())
                        .bidQty(record.getBidSize())
                        .build())
                .collect(Collectors.toList());
    }
}
