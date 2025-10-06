package com.trans.web_trans_java.exception;

import com.trans.web_trans_java.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"com.trans.web_trans_java.controller"})
@Slf4j
public class GlobalExceptionHandler {
    // 处理所有 RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public Result<ExceptionData<?>> GlobalRuntimeExceptionError(RuntimeException e, ErrorResponse data){
        log.error("运行异常: ", e);
        return Result.failWithData(data.getCode(),"运行异常", data.getData());
    }
    // 处理所有 Exception
    @ExceptionHandler(Exception.class)
    public Result<ExceptionData<?>> GlobalExceptionError(Exception e, ErrorResponse data){
        log.error("全局异常: ", e);
        return Result.failWithData(data.getCode(),"全局异常", data.getData());
    }
}
