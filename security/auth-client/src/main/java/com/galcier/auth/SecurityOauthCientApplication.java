package com.galcier.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-02-21 11:13
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SecurityOauthCientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityOauthCientApplication.class, args);
    }
}
