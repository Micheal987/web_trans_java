package com.trans.web_trans_java.config;

import lombok.Data;

@Data
public class Config {
    private EmailConfig emailConfig;
    private EsConfig esConfig;
    private Ip2regionConfig ip2regionConfig;
    private QiNiuConfig qiNiuConfig;
}
