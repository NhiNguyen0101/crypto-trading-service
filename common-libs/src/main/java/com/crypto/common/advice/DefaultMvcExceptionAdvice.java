package com.crypto.common.advice;

import com.crypto.common.exeptions.ResourceNotFoundException;
import com.crypto.common.pojo.BaseErrorResult;
import com.crypto.common.pojo.ResultBuilder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

public abstract class DefaultMvcExceptionAdvice {
    /**
     * ConstraintViolationException is thrown when Controller
     * accepts @RequestBody List<@Valid UpdateSpecialPrioritiesRequest> requests
     * and each list item's validations aren't met
     */
    @ExceptionHandler({
            ValidationException.class,
            MethodArgumentNotValidException.class,
            BindException.class,
            MissingServletRequestParameterException.class,
            ConstraintViolationException.class,
    })
    public ResponseEntity<BaseErrorResult> handle400ValidationFailed(Exception ex) {

        return new ResponseEntity<>(
                ResultBuilder.newResult()
                        .message("Validation failed")
                        .details(ex.getMessage())
                        .buildError(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            TypeMismatchException.class
    })
    public ResponseEntity<BaseErrorResult> handle400DeserializationFailed(Exception ex) {
        return new ResponseEntity<>(
                ResultBuilder.newResult()
                        .message("Bad request query or body")
                        .details(ex.getMessage())
                        .buildError(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            NoHandlerFoundException.class,
            ResourceNotFoundException.class,
    })
    public ResponseEntity<BaseErrorResult> handle404Requests(Exception ex) {
        return new ResponseEntity<>(
                ResultBuilder.newResult()
                        .message("No such resource")
                        .details(ex.getMessage())
                        .buildError(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<BaseErrorResult> handle405Requests(Exception ex) {

        return new ResponseEntity<>(
                ResultBuilder.newResult()
                        .message("Method not allowed")
                        .details(ex.getMessage())
                        .buildError(),
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<BaseErrorResult> handle415Requests(Exception ex) {

        return new ResponseEntity<>(
                ResultBuilder.newResult()
                        .message("Content-type not supported")
                        .details(ex.getMessage())
                        .buildError(),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseErrorResult> unhandledException(Exception ex) {

        return new ResponseEntity<>(
                ResultBuilder.newResult()
                        .message(ex.getMessage())
                        .details(ExceptionUtils.getStackTrace(ex))
                        .buildError(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
