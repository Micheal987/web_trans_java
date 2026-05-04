package com.trans.web_trans_java.utils.pwd;

import com.trans.web_trans_java.config.yaml_conf.Conf;
import com.trans.web_trans_java.config.yaml_conf.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

//Spring 
public class Pwd {
    /**
     * 對明文密碼進行 bcrypt 哈希
     *
     * @param plainPassword 明文密碼
     * @return bcrypt 哈希字符串（包含自動生成的隨機鹽值）
     */
    public static String encrypt(String plainPassword) {
        Configuration conf = null;
        try {
            conf = Conf.InitConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 從配置讀取強度（Strength），若無則預設為 10
        int strength = (conf != null && conf.getCrypto() != null)
                ? conf.getCrypto().getBcryptStrength()
                : 10;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(strength);
        return encoder.encode(plainPassword);
    }

    /**
     * 驗證明文密碼是否與存儲的 bcrypt 哈希匹配
     *
     * @param plainPassword  明文密碼
     * @param hashedPassword 資料庫中存儲的 bcrypt 哈希
     * @return true 如果匹配
     */
    public static boolean decrypt(String plainPassword, String hashedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(plainPassword, hashedPassword);
    }
}
