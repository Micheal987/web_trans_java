package com.trans.web_trans_java.config;

import lombok.Data;

@Data
public class EsConfig {
    private String host;
    private Integer port;
    private String user;
    private String password;
    private Integer maxRetries;
   public EsConfig(String host, Integer port, String user, String password, Integer maxRetries) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.maxRetries = maxRetries;
    }
}
