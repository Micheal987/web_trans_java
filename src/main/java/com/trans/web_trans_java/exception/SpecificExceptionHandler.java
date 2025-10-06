package com.trans.web_trans_java.exception;

import com.trans.web_trans_java.common.enums.CodeEnums;
import com.trans.web_trans_java.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

// 特定异常处理器
@RestControllerAdvice(basePackages = {"com.trans.web_trans_java.controller"})
@Slf4j
public class SpecificExceptionHandler {
    // 处理数据不存在异常
    @ExceptionHandler(NoSuchElementException.class)
    //Void
    public Result<Optional<String[]>> SpecificExceptionError(Exception e){
        log.error(e.getMessage());
        return Result.failWithData(CodeEnums.NotFound,"请求的资源不存在",Optional.empty());
    }
    //处理参数验证异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<FieldError> ResponseEntity(MethodArgumentNotValidException e){
        String field = Objects.requireNonNull(e.getBindingResult().getFieldError()).getField();
        String message = Objects.requireNonNull(e.getBindingResult().getGlobalError()).getDefaultMessage();
        FieldError res = new FieldError(field,message);
        return  Result.failWithData(CodeEnums.FAIL,"参数验证失败",res);
    }
}
