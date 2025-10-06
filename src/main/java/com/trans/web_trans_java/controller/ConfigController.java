package com.trans.web_trans_java.controller;

import com.trans.web_trans_java.common.result.Result;
import com.trans.web_trans_java.config.Config;
import com.trans.web_trans_java.core.InitConfig;
import com.trans.web_trans_java.core.enums.ConfigEnums;
import com.trans.web_trans_java.entity.UserModel;
import com.trans.web_trans_java.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = {"/config"})
public class ConfigController {
    @Resource
    UserService userService;

    @GetMapping("/index")
    public Result<UserModel> index() {
        return Result.successWithData(userService.getUserMapper(1));
    }

    @GetMapping("/c")
    public Result<Config> all() throws IOException {
        return Result.successWithData(InitConfig.ReadConfig());
    }

    @GetMapping("/c/{type}")
    public Result<Object> all1(@PathVariable("type") ConfigEnums type) throws IOException {
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
