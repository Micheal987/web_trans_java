package com.trans.web_trans_java.common.enums;


import lombok.Getter;

public enum CodeEnums {
    SUCCESS(200),
    FAIL(400),
    InternalError(500),
    NotFound(404);
    @Getter
    private final int code;

    CodeEnums(int code) {
        this.code = code;
    }
}
