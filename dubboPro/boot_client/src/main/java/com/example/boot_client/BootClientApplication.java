package com.example.boot_client;

import com.dubbo.client.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
@EnableDubbo
public class BootClientApplication {

    @Reference
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BootClientApplication.class, args).close();
    }

    @Bean
    public ApplicationRunner getBean() {
        return args -> {
            System.out.println(userService.getUser(1));
        };
    }

}
