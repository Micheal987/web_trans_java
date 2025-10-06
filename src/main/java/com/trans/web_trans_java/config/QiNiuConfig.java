package com.trans.web_trans_java.config;

import com.electronwill.nightconfig.core.file.FileConfig;
import lombok.Data;

@Data
public class QiNiuConfig {
    private boolean enable;
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String cdn;
    private String domain;
    private String zone;
    private String prefix;
    private Integer size;
    private String recordDir;

    public QiNiuConfig(boolean enable, String accessKey, String secretKey, String bucketName, String cdn, String domain, String zone, String prefix, Integer size, String recordDir) {
        this.enable = enable;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucketName = bucketName;
        this.cdn = cdn;
        this.domain = domain;
        this.zone = zone;
        this.prefix = prefix;
        this.size = size;
        this.recordDir = recordDir;

    }
}
