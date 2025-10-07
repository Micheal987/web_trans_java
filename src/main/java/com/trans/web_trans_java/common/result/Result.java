package com.trans.web_trans_java.common.result;

import com.trans.web_trans_java.common.enums.CodeEnums;
import lombok.Data;

import java.util.Optional;

@Data
public class Result<T> {
    private CodeEnums code;
    private String message;
    private T data;

    //主构造器
    private Result(CodeEnums code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //构造方法
    public static <T> Result<T> newResultResponse(CodeEnums code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static Result<Void> successWithMsg(String message) {

        return newResultResponse(CodeEnums.SUCCESS, message, null);
    }

    public static <T> Result<T> successWithData(T data) {
        return newResultResponse(CodeEnums.SUCCESS, Enter.SUCCESS_MSG, data);
    }

    public static <T> Result<T> successWithData(String message, T data) {
        return newResultResponse(CodeEnums.SUCCESS, message, data);
    }

    public static Result<Void> failWithMsg(String message) {

        return newResultResponse(CodeEnums.FAIL, message, null);
    }

    public static Result<Void> failWithMsgDefault() {

        return newResultResponse(CodeEnums.FAIL, Enter.FAIL_MSG, null);
    }

    public static <T> Result<T> failWithData(CodeEnums code, String message, T data) {

        return newResultResponse(code, message, data);
    }

    public static <T> Result<T> failWithData(String message, T data) {

        return newResultResponse(CodeEnums.FAIL, message, data);
    }

    public static Result<Optional<String[]>> failWithData(CodeEnums code, String message) {

        return newResultResponse(CodeEnums.FAIL, message, Optional.empty());
    }

    public static <T> Result<T> failWithData(T data) {

        return newResultResponse(CodeEnums.FAIL, Enter.FAIL_MSG, data);
    }

}
