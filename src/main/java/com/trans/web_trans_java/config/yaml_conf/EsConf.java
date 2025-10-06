package com.trans.web_trans_java.config.yaml_conf;

import lombok.Data;

@Data
public class EsConf {
    private String host;
    private Integer port;
    private String userName;
    private String password;
    private String maxRetries;
}
