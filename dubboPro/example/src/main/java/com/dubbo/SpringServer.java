package com.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class SpringServer {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("provide.xml");
        System.out.println("服务已暴露");
        System.in.read();
    }
}
