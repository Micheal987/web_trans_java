package com.trans.web_trans_java.exception;

import com.trans.web_trans_java.common.enums.CodeEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorResponse extends RuntimeException {
    private CodeEnums code;
    private String msg;
    private ExceptionData<?> data;
    // 构造函数，接收错误代码和消息
    public ErrorResponse(CodeEnums code, String msg) {
        super(msg);  // 将消息传递给父类的构造函数 (RuntimeException)
        this.code = code;
        this.msg = msg;
    }
    public ErrorResponse(CodeEnums code, String msg, ExceptionData<?> data) {
        super(msg); // 将消息传递给父类的构造函数 (RuntimeException)
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
