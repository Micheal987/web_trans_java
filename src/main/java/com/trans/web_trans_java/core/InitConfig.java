package com.trans.web_trans_java.core;

import com.electronwill.nightconfig.core.file.FileConfig;

import com.trans.web_trans_java.config.*;
import com.trans.web_trans_java.core.enums.ConfigEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//toml读取
//java 这个读取非常烂
//没有好的库
@Slf4j
public class InitConfig {
    private static FileConfig tomlConfiguration() throws IOException {
        // 创建资源对象来加载配置文件
        ClassPathResource resource = new ClassPathResource("/config/setting.toml");

        try {
            // 检查资源是否是文件并获取文件路径
            if (!resource.isFile()) {
                throw new IOException("配置文件路径无效或不是一个文件");
            }

            // 获取文件对象
            File configFile = resource.getFile();

            //  FileConfig 加载配置
            FileConfig config = FileConfig.of(configFile);
            config.load();

            return config;
        } catch (FileNotFoundException e) {
            // 配置文件不存在的异常
            log.error("配置文件不存在: {}", e.getMessage());
            throw e;
        } catch (IOException e) {
            // 文件读取相关的异常
            log.error("无法读取配置文件: {}", e.getMessage());
            throw e;
        }
    }

    public static Config ReadConfig() throws IOException {
        FileConfig tomlParser = tomlConfiguration();
        //Ip2regionConfig
        Ip2regionConfig ip2regionConfig = new Ip2regionConfig();
        ip2regionConfig.setFilepath(tomlParser.get("ip2region.filepath"));
        //EmailConfig
        EmailConfig email = ReadEmailConfig(tomlParser);
        //es
        EsConfig es = ReadEsConfig(tomlParser);
        //qiNiu
        QiNiuConfig qiNiu = ReadQiNiuConfig(tomlParser);
        //Config
        Config config = new Config();
        config.setEmailConfig(email);
        config.setEsConfig(es);
        config.setQiNiuConfig(qiNiu);
        return config;

    }

    private static EmailConfig ReadEmailConfig(FileConfig tomlParser) {
        String c = "email.";
        String from = tomlParser.get(c + "from");
        String to = tomlParser.get(c + "to");
        String reply_to = tomlParser.get(c + "reply_to");
        String smtp_username = tomlParser.get(c + "smtp_username");
        String smtp_password = tomlParser.get(c + "smtp_password");
        return new EmailConfig(from, to, reply_to, smtp_username, smtp_password);
    }

    private static EsConfig ReadEsConfig(FileConfig tomlParser) {
        String c = "es.";
        String host = tomlParser.get(c + "host");
        Integer port = tomlParser.getInt(c + "port");
        String user = tomlParser.get(c + "user");
        String password = tomlParser.get(c + "password");
        Integer maxRetries = tomlParser.getInt(c + "max_retries");
        return new EsConfig(host, port, user, password, maxRetries);
    }

    private static QiNiuConfig ReadQiNiuConfig(FileConfig tomlParser) {
        String c = "qiniu.";
        boolean enable = tomlParser.get(c + "enable");
        String accessKey = tomlParser.get(c + "access_key");
        String secretKey = tomlParser.get(c + "secret_key");
        String bucketName = tomlParser.get(c + "bucket_name");
        String cdn = tomlParser.get(c + "cdn");
        String domain = tomlParser.get(c + "domain");
        String zone = tomlParser.get(c + "zone");
        String prefix = tomlParser.get(c + "prefix");
        Integer size = tomlParser.getInt(c + "size");
        String recordDir = tomlParser.get(c + "record_dir");
        return new QiNiuConfig(enable, accessKey, secretKey, bucketName, cdn, domain, zone, prefix, size, recordDir);
    }

    public boolean setConfig(Config conf, ConfigEnums type) {
        return switch (type) {
            case EmailConfig -> {
                conf.setEmailConfig(conf.getEmailConfig());
                yield true;
            }
            case EsConfig -> {
                conf.setEsConfig(conf.getEsConfig());
                yield true;
            }
            case QiNiuConfig -> {
                conf.setQiNiuConfig(conf.getQiNiuConfig());
                yield true;
            }
            case Ip2regionConfig -> {
                conf.setIp2regionConfig(conf.getIp2regionConfig());
                yield true;
            }
            default -> false;
        };

    }
}
