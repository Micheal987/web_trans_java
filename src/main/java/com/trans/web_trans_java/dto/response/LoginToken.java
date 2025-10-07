package com.trans.web_trans_java.dto.response;

import lombok.Data;


@Data
public class LoginToken {
    private String token;
    public LoginToken(String token) {
        this.token = token;
    }
}
