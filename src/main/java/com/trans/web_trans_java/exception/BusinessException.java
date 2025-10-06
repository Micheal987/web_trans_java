package com.trans.web_trans_java.exception;

import com.trans.web_trans_java.common.enums.CodeEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.core.annotation.Order;

@EqualsAndHashCode(callSuper = true)
@Data
@Order(value = 1)
public class BusinessException extends  RuntimeException {
    private CodeEnums errorCode;
    public BusinessException(CodeEnums errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
