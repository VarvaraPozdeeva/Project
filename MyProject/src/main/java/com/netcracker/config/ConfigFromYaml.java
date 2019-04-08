package com.netcracker.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("arangodb")
@Data
public class ConfigFromYaml {

    private String database;
    private String user;
    private String password;
    private String host;
    private int port;
}
