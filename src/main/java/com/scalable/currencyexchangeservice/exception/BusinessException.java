package com.scalable.currencyexchangeservice.exception;

import org.springframework.http.HttpStatus;

import static java.lang.String.format;

public class BusinessException extends RuntimeException {

    private final String code;

    private final String message;

    private final HttpStatus httpStatus;

    public BusinessException(final BusinessExceptionReason businessExceptionReason) {
        this.code = businessExceptionReason.getCode();
        this.message = businessExceptionReason.getMessage();
        this.httpStatus = businessExceptionReason.getHttpStatus();
    }


    public BusinessException(final BusinessExceptionReason reason, String argument) {
        if (!argument.isEmpty()) {
            this.message = reason.getMessage().concat(argument);
        } else {
            this.message = reason.getMessage();
        }

        this.code = reason.getCode();
        this.httpStatus = reason.getHttpStatus();
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
