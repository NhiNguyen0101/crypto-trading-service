package com.crypto.common.pojo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultBuilder {

    private final CommonStatus status = new CommonStatus();

    public static ResultBuilder newResult() {
        return new ResultBuilder();
    }

    public ResultBuilder success() {
        status.setCode(String.valueOf(HttpStatus.SC_OK));
        return this;
    }

    public ResultBuilder message(String message) {
        status.setMessage(message);
        return this;
    }

    public ResultBuilder details(String details) {
        status.setDetails(details);
        return this;
    }

    public <T> BaseObjectResult<T> build(T data) {

        BaseObjectResult<T> result = new BaseObjectResult<>();
        result.setCommonStatus(status);
        result.setData(data);

        return result;
    }

    public BaseErrorResult buildError() {
        BaseErrorResult result = new BaseErrorResult();
        result.setCommonStatus(status);

        return result;
    }
}
