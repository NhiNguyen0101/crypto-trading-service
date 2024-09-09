package com.crypto.client.dataGateway.houbiApiGateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class HoubiPriceData {

    @JsonProperty("symbol")
    String symbol;

    @JsonProperty("bid")
    String bid;

    @JsonProperty("bidSize")
    String bidSize;

    @JsonProperty("ask")
    String ask;

    @JsonProperty("askSize")
    String askSize;
}
