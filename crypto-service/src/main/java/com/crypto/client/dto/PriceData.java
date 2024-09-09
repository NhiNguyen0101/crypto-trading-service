package com.crypto.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class PriceData {

    @JsonProperty("symbol")
    String symbol;

    @JsonProperty("bidPrice")
    String bidPrice;

    @JsonProperty("bidQty")
    String bidQty;

    @JsonProperty("askPrice")
    String askPrice;

    @JsonProperty("askQty")
    String askQty;
}
