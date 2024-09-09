package com.crypto.client.dataGateway.houbiApiGateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class HoubiPriceDataResponse {

    @JsonProperty("data")
    List<HoubiPriceData> priceDataSet;
}
