package org.geekhub.niko.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class ApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);

        Properties properties = System.getProperties();

        System.out.println(properties.get("spring.datasource.url"));
        System.out.println(properties.get("spring.datasource.username"));
        System.out.println(properties.get("spring.datasource.password"));
    }
}
