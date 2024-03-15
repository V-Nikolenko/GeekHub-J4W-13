package org.geekhub.niko.test;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }

    @Bean
    public InitializingBean printProperties(@Value("${spring.datasource.url}") String url,
                                            @Value("${spring.datasource.username}") String username,
                                            @Value("${spring.datasource.password}") String password) {
        return () -> {
            System.out.println(url);
            System.out.println(username);
            System.out.println(password);
        };
    }
}
