package com.trans.web_trans_java.utils.pwd;



import com.trans.web_trans_java.config.yaml_conf.Conf;
import com.trans.web_trans_java.config.yaml_conf.Configuration;
import com.trans.web_trans_java.config.yaml_conf.CryptoConf;
import org.bouncycastle.crypto.generators.OpenBSDBCrypt;

import java.io.IOException;

public class EncryptionPwd {

    //这个包加密需要你自己控制（生成随机16位byte来哈希）否则同一个密码的加密密钥会相同

    /**
     * 对明文密码进行 bcrypt 哈希
     *
     * @param plainPassword 明文密码
     * @return bcrypt 哈希字符串（例如 $2a$10$...）
     */
    public static String encrypt(String plainPassword) {
        try {
            Configuration conf = Conf.InitConfig();
            assert conf != null;
            CryptoConf pwd_conf = conf.getCrypto();
            return OpenBSDBCrypt.generate(plainPassword.getBytes(), pwd_conf.getKey().getBytes(), pwd_conf.getBcryptStrength());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 验证明文密码是否与存储的 bcrypt 哈希匹配
     *
     * @param plainPassword  明文密码
     * @param hashedPassword 数据库中存储的 bcrypt 哈希
     * @return true 如果匹配
     */
    public static boolean decrypt(String plainPassword, String hashedPassword) {
        return OpenBSDBCrypt.checkPassword(hashedPassword, plainPassword.toCharArray());
    }
}
