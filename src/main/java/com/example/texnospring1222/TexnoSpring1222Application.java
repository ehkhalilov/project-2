package com.example.texnospring1222;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableFeignClients
@EnableWebSecurity
public class TexnoSpring1222Application {

    public static void main(String[] args) {
        SpringApplication.run(TexnoSpring1222Application.class, args);
    }

}
