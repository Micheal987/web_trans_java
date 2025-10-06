package com.trans.web_trans_java.dto.response;

import lombok.Data;

import java.util.Timer;

@Data
public class LoginToken {
    private String token;
    private Timer timer;
    public  LoginToken(String token) {
        this.token = token;
        timer = new Timer();
    }
}
