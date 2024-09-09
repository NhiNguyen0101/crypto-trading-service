package com.crypto.common.config.feign.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FeignErrorModel {

    @Schema(description = "The service that threw this error")
    @JsonProperty("service_type")
    private ServiceType serviceType;

    @Schema(description = "The error code returned by the feign service. This is NOT the http status code")
    @JsonProperty("code")
    private int code;

    @Schema(description = "The error message returned by the feign service")
    @JsonProperty("message")
    private String message;

    @Schema(description = "Detailed error message returned by the feign service")
    @JsonProperty("details")
    private String details;
}
