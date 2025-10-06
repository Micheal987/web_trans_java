package com.trans.web_trans_java.config.yaml_conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class Conf {
    public static Configuration InitConfig() throws IOException {
        try (InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream("config/crypto.yaml")) {
            if (inputStream == null) {
                throw new IOException("Configuration file not found");
            }
            ObjectMapper mapper = new YAMLMapper();
            return mapper.readValue(inputStream, Configuration.class);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
