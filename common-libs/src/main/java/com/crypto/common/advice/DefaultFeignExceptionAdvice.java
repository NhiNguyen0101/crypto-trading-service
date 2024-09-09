package com.crypto.common.advice;

import com.crypto.common.pojo.BaseErrorResult;
import com.crypto.common.pojo.ResultBuilder;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class DefaultFeignExceptionAdvice {
    /**
     * This handles 500 level http responses returned by feign servers.
     */
    @ExceptionHandler(FeignException.FeignServerException.class)
    public ResponseEntity<BaseErrorResult> feignServerException(
            FeignException.FeignServerException ex) {

        return new ResponseEntity<>(
                ResultBuilder.newResult()
                        .message("Unrecoverable 500 level error from feign service")
                        .details(ex.getMessage())
                        .buildError(),
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * This handles 404 http responses returned by feign servers.
     */
    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<BaseErrorResult> feignClient404Exception(FeignException.NotFound ex) {

        return new ResponseEntity<>(
                ResultBuilder.newResult()
                        .message("Resource not found at feign service")
                        .details(ex.getMessage())
                        .buildError(),
                HttpStatus.NOT_FOUND);
    }

    /**
     * This handles 400 level http responses returned by feign servers.
     */
    @ExceptionHandler(FeignException.FeignClientException.class)
    public ResponseEntity<BaseErrorResult> feignClientException(FeignException.FeignClientException ex) {

        return new ResponseEntity<>(
                ResultBuilder.newResult()
                        .message("Unexpected 400 level error from feign service")
                        .details(ex.getMessage())
                        .buildError(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This handles exceptions thrown by feign themselves. This may include serialization, and
     * deserialization exceptions.
     */
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<BaseErrorResult> feignException(FeignException ex) {

        return new ResponseEntity<>(
                ResultBuilder.newResult()
                        .message("Unhandled exception thrown by feign code")
                        .details(ex.getMessage())
                        .buildError(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
