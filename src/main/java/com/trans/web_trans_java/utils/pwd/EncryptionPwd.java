package com.trans.web_trans_java.utils.pwd;


import com.trans.web_trans_java.config.yaml_conf.Conf;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Objects;

public class EncryptionPwd {
    private static final String ALGORITHM = "AES"; // 加密算法
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding"; // 加密模式和填充方式
    private static final String IV = "1234567890123456"; // IV（初始化向量）


    private static final SecretKey secretKey; // 存储生成的密钥

    static {
        Security.addProvider(new BouncyCastleProvider()); // 添加 Bouncy Castle 作为安全提供者
        try {
            secretKey = generateKey(); // 生成密钥
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate AES key", e); // 捕获异常并抛出运行时异常
        }
    }

    // 生成 AES 密钥
    private static SecretKey generateKey() throws Exception {
        String pwd = Objects.requireNonNull(Conf.InitConfig()).getCrypto().getKey();
        byte[] salt = new byte[16]; // 加密盐
        KeySpec spec = new PBEKeySpec(pwd.toCharArray(), salt, 10000, 128); // 使用 PBKDF2 派生密钥
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256"); //生成一个固定长度的密钥 //PBKDF2WithHmacSHA256是加密算法
        byte[] key = factory.generateSecret(spec).getEncoded();//加密算法的密钥
        return new javax.crypto.spec.SecretKeySpec(key, ALGORITHM); // 生成并返回密钥

    }

    // 加密方法
    public static String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION); // 获取 Cipher 实例
        IvParameterSpec ivParams = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)); // 创建 IV 参数规范
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams); // 初始化 Cipher 为加密模式
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)); // 执行加密
        return Base64.getEncoder().encodeToString(encryptedBytes); // 返回 Base64 编码的加密结果
    }

    // 解密方法
    public static String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION); // 获取 Cipher 实例
        IvParameterSpec ivParams = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)); // 创建 IV 参数规范
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams); // 初始化 Cipher 为解密模式
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData)); // 执行解密
        return new String(decryptedBytes, StandardCharsets.UTF_8); // 返回解密后的字符串
    }
}
