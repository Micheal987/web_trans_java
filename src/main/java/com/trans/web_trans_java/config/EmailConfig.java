package com.trans.web_trans_java.config;

import lombok.Builder;
import lombok.With;


@Builder
@With
public  record  EmailConfig (String from,String to,String reply_to,String smtp_username,String smtp_password) {
}
