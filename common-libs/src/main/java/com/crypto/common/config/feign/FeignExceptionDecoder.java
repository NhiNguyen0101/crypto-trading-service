package com.crypto.common.config.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.crypto.common.config.feign.models.FeignErrorModel;
import com.crypto.common.utils.JacksonUtility;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class FeignExceptionDecoder implements ErrorDecoder {
    private static final ObjectMapper JSON_MAPPER = JacksonUtility.getStandardJsonMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        FeignException feignException = FeignException.errorStatus(methodKey, response);

        String body = feignException.contentUTF8();
        if (StringUtils.isEmpty(body)) {
            return feignException;
        }

        FeignErrorModel error;
        try {
            error = JSON_MAPPER.readValue(body, FeignErrorModel.class);
        } catch (IOException e) {
            return feignException;
        }

        if (error == null) {
            return feignException;
        }

        return feignException;
    }
}
