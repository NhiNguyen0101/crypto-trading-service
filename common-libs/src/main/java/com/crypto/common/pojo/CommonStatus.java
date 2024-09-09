package com.crypto.common.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CommonStatus {

    @Schema(description = "A special status code")
    @JsonProperty("code")
    private String code;

    @Schema(description = "An extra message to describe")
    @JsonProperty("message")
    private String message;

    @Schema(description = "Detailed messages")
    @JsonProperty("details")
    private String details;
}
