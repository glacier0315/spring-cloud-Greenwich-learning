package com.glacier.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author glacier
 * @version 1.0
 * @date 2019-12-23 10:31
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AuthResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthResourceApplication.class, args);
    }

}
