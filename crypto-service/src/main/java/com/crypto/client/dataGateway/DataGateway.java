package com.crypto.client.dataGateway;

import com.crypto.client.dto.PriceDataResponse;
import com.crypto.common.enums.TenantType;
import com.crypto.common.pojo.BaseObjectResult;
import com.crypto.dto.CryptoPriceDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataGateway {

    @Autowired
    private DataGatewayFeignClient feignClient;

    @SneakyThrows
    public List<CryptoPriceDto> getBinanceAllPrices() {

        return getCryptoPrices(feignClient.getBinanceBitcoinEthereumPrices(), TenantType.BINANCE);
    }

    @SneakyThrows
    public List<CryptoPriceDto> getHoubiAllPrices() {

        return getCryptoPrices(feignClient.getHoubiBitcoinEthereumPrices(), TenantType.HOUBI);
    }

    private List<CryptoPriceDto> getCryptoPrices(BaseObjectResult<PriceDataResponse> dataSet, TenantType tenantType) {

        if (dataSet == null ||
                dataSet.getData() == null ||
                CollectionUtils.isEmpty(dataSet.getData().getPriceDataSet())) {
            return Collections.emptyList();
        }

        return dataSet.getData().getPriceDataSet().stream()
                .map(v -> CryptoPriceDto.builder()
                        .tenant(tenantType.name())
                        .symbol(v.getSymbol())
                        .askPrice(Double.valueOf(v.getAskPrice()))
                        .askQty(Double.valueOf(v.getAskQty()))
                        .bidPrice(Double.valueOf(v.getBidPrice()))
                        .bidQty(Double.valueOf(v.getBidQty()))
                        .build())
                .collect(Collectors.toList());
    }
}
