package com.luzhiqing.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/28 16:39
 */
@RestControllerAdvice(basePackages = "com.luzhiqing.bamboo")
public class GlobalExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public RestResult<?> CcbExceptionHandler(Exception e, HttpServletResponse response){
        log.error("error: ", e);
        return new RestResult<>(e);
    }
}
