package com.crypto.advice;

import com.crypto.common.advice.DefaultFeignExceptionAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FeignExceptionAdvice extends DefaultFeignExceptionAdvice {
}
