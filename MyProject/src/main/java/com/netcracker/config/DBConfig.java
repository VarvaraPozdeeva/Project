package com.netcracker.config;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.AbstractArangoConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableArangoRepositories(basePackages = {"com.netcracker"})
@RequiredArgsConstructor
public class DBConfig extends AbstractArangoConfiguration {

    private final ConfigFromYaml config;

    @Override
    public ArangoDB.Builder arango() {
        return new ArangoDB.Builder().host(config.getHost(), config.getPort())
                .user(config.getUser()).password(config.getPassword());
    }
    @Override
    public String database() {
        return config.getDatabase();
    }
}
