package com.trans.web_trans_java.exception;

import com.trans.web_trans_java.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;
// 自定义异常处理器
@RestControllerAdvice(basePackages = {"com.trans.web_trans_java.controller"})
public class BusinessExceptionHandler {
    //BusinessException
    @ExceptionHandler(value =BusinessException.class)
    //多数情况 是 Void
    public Result<Optional<String[]>> BusinessExceptionHandlerError(BusinessException e){
        String message = e.getMessage();
        return Result.failWithData(e.getErrorCode(),message);
    }
}
