package com.trans.web_trans_java.config;

import lombok.Data;

@Data
public class EmailConfig {
    private String from;
    private String to;
    private String reply_to;
    private String smtp_username;
    private String smtp_password;
    public  EmailConfig(String from, String to, String reply_to, String smtp_username, String smtp_password) {
        this.from = from;
        this.to = to;
        this.reply_to = reply_to;
        this.smtp_username = smtp_username;
        this.smtp_password = smtp_password;
    }
}
