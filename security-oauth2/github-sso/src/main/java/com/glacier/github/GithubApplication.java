package com.glacier.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author glacier
 * @version 1.0
 * @date 2020-02-20 18:27
 */
@EnableOAuth2Sso
@EnableDiscoveryClient
@SpringBootApplication
public class GithubApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubApplication.class, args);
    }

}