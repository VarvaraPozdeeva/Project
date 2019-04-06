package com.netcracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(final String ...args) {
        //final Class<?>[] runner = new Class<?>[] {CrudRunner.class, AQLRunner.class};
        //System.exit(SpringApplication.exit(SpringApplication.run(runner, args)));
        SpringApplication.run(Application.class, args);
    }
}
