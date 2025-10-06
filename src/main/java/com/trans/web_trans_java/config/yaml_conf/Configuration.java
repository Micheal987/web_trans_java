package com.trans.web_trans_java.config.yaml_conf;

import lombok.Data;

import java.util.List;

@Data
public class Configuration {
    private CryptoConf crypto;
    private List<EsConf> es;
}
