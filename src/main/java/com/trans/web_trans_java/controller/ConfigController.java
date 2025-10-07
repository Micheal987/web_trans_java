package com.trans.web_trans_java.controller;

import com.trans.web_trans_java.common.result.Result;
import com.trans.web_trans_java.config.Config;
import com.trans.web_trans_java.core.InitConfig;
import com.trans.web_trans_java.core.enums.ConfigEnums;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = {"/config"})
public class ConfigController {

    @GetMapping("/list")
    public Result<Config> confView() throws IOException {
        return Result.successWithData(InitConfig.ReadConfig());
    }

    @GetMapping("/{type}")
    public Result<?> confView(@PathVariable("type") ConfigEnums type) throws IOException {
        Config c = InitConfig.ReadConfig();
        return switch (type) {
            case EsConfig -> Result.successWithData(c.getEsConfig());
            case EmailConfig -> Result.successWithData(c.getEmailConfig());
            case QiNiuConfig -> Result.successWithData(c.getQiNiuConfig());
            case Ip2regionConfig -> Result.successWithData(c.getIp2regionConfig());
            default -> Result.failWithData("配置不存在");
        };

    }
}
