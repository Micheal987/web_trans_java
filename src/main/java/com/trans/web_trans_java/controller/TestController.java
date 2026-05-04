package com.trans.web_trans_java.controller;

import com.trans.web_trans_java.common.result.Result;
import com.trans.web_trans_java.utils.pwd.EncryptionPwd;
import com.trans.web_trans_java.utils.pwd.Pwd;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @GetMapping("/pwd")
    Result<String> test_pwd () throws Exception {
        String pwd = "dasdas";
       String newPwd = Pwd.encrypt(pwd);
        boolean ve = Pwd.decrypt(pwd,newPwd);
        if(!ve){
            return  Result.failWithData("Err");
        }
        return  Result.successWithData(newPwd);
    }
}
