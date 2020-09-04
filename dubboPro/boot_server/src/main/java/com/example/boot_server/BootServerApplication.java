package com.example.boot_server;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class BootServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootServerApplication.class, args);
        System.out.println("服务已启动");
    }

}
