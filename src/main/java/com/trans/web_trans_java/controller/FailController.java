package com.trans.web_trans_java.controller;

import com.trans.web_trans_java.common.enums.CodeEnums;
import com.trans.web_trans_java.common.result.Result;
import com.trans.web_trans_java.config.yaml_conf.Conf;
import com.trans.web_trans_java.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(path = "fail")
public class FailController {
    @GetMapping("/res")
    public Result<Void> failView() {
        return Result.failWithMsgDefault();
    }

    @GetMapping("global")
    public String failGlobalView() {
        throw new RuntimeException();
    }

    @GetMapping("Business")
    public String failBusinessExceptionView() {
        throw new BusinessException(CodeEnums.FAIL, "fail");
    }
    // 模拟参数校验异常
    @GetMapping("/valid/{id}")
    public Result<Void> testValidationView(@PathVariable String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID不能为空");
        }
        return Result.successWithMsg("ID: " + id);
    }
}
