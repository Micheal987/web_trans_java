package com.trans.web_trans_java.config;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record EsConfig (String host,Integer port, String user,String pwd ,Integer maxRetries) {

}
