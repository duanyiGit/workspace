package com.dubbo;

import com.dubbo.client.UserService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConfigCenterConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

public class DubboServer {

    public static void main(String[] args) throws IOException {
        // 当前应用配置
        ApplicationConfig applicationConfig
                = new ApplicationConfig("sample-app");
        // 服务提供者协议配置
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(-1);
        // 连接注册中心配置
        //RegistryConfig registryConfig = new RegistryConfig("multicast://224.5.6.7:1234?unicast=false");
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://192.168.150.8:2181");
        //2.7.7配置连接超时时间
        //registryConfig.setTimeout(20000);
        //注意：2.7.4.1版本zookeeper连接需要配置连接超时时间，默认为3秒超时。
        // 使用的是ConfigCenterConfig配置，而不是RegistryConfig
        ConfigCenterConfig configCenterConfig = new ConfigCenterConfig();
        configCenterConfig.setTimeout(30000L);
        /*RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("10.20.130.230:9090");*/
        // 服务提供者暴露服务配置
        ServiceConfig serviceConfig = new ServiceConfig();
        serviceConfig.setInterface(UserService.class);
        serviceConfig.setRef(new UserServiceImpl());
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setConfigCenter(configCenterConfig);
        serviceConfig.setApplication(applicationConfig);
        // 暴露及注册服务
        serviceConfig.export();
        System.out.println("服务已暴露");
        System.in.read();

    }
}
