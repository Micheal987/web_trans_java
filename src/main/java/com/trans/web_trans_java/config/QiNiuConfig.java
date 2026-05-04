package com.trans.web_trans_java.config;


import lombok.Builder;
import lombok.With;

@Builder
@With
public record QiNiuConfig(
        boolean enable,
        String accessKey,
        String secretKey,
        String bucketName,
        String cdn,
        String domain,
        String zone,
        String prefix,
        Integer size,
        String recordDir
) {
}
