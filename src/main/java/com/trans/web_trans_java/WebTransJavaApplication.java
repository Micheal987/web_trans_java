package com.trans.web_trans_java;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.trans.web_trans_java.mapper")
public class WebTransJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebTransJavaApplication.class, args);
    }

}
