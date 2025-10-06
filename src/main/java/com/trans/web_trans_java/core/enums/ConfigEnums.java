package com.trans.web_trans_java.core.enums;

import lombok.Getter;

public enum ConfigEnums {
    QiNiuConfig(1),
    EmailConfig(2),
    EsConfig(3),
    Ip2regionConfig(4);
    @Getter
    private final int code;
     ConfigEnums(int code) {
        this.code = code;
    }
}
