package com.scalable.currencyexchangeservice.exception;

import com.scalable.currencyexchangeservice.dto.ErrorResponseDto;
import com.scalable.currencyexchangeservice.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalApiExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalApiExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleUncaughtException(final Exception ex, final ServletWebRequest request) {
        logger.error(request.toString(), ex);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(Exception.class.getSimpleName()
                , HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtil.getCurrentTimeStamp());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleCustomUncaughtBusinessException(final BusinessException ex
            , final ServletWebRequest request) {
        logger.error(request.toString(), ex);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getCode()
                , ex.getMessage(), ex.getHttpStatus().value(), CommonUtil.getCurrentTimeStamp());
        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponseDto);
    }
}
