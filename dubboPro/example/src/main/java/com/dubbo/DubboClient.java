package com.dubbo;

import com.dubbo.client.UserService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConfigCenterConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DubboClient {
    public static void main(String[] args) throws IOException {
        // 当前应用配置
        ApplicationConfig applicationConfig = new ApplicationConfig("young-app");
        // 连接注册中心配置
       // RegistryConfig registryConfig = new RegistryConfig("multicast://224.5.6.7:1234?unicast=false");
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://192.168.150.8:2181");
        /*RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("10.20.130.230:9090");*/
        //注意：zookeeper连接需要配置连接超时时间，默认为3秒超时。使用的是ConfigCenterConfig配置的超时时间，而不是RegistryConfig
        ConfigCenterConfig configCenterConfig = new ConfigCenterConfig();
        configCenterConfig.setTimeout(20000L);
        // 引用远程服务
        ReferenceConfig referenceConfig = new ReferenceConfig();
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setTimeout(30000);
        referenceConfig.setInterface(UserService.class);
        referenceConfig.setConfigCenter(configCenterConfig);
        //消费端指定了group时，服务提供者也需要指定相同名字的组
        //referenceConfig.setGroup("test");
        // referenceConfig.setFilter("-firstFilter");
        UserService userService = (UserService) referenceConfig.get();

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String line = buffer.readLine();
            if (line.equals("quit")) {
                break;
            }
            System.out.println(userService.getUser(1));
        }

    }
}
