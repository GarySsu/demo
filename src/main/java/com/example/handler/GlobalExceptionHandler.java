package com.example.handler;

import com.example.handler.exception.CustomException;
import com.example.resp.ErrorResp;
import com.example.resp.RespCode;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author garyssu
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ErrorResp customException(Exception ex) {
        log.error("Unexpected error occurred: {}", ex.getMessage());
        return new ErrorResp(ex.getMessage());
    }

}
