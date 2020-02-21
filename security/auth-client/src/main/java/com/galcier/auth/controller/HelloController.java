package com.galcier.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-11 13:50
 */
@Slf4j
@RestController
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello(String name) {
        return "hello " + name;
    }


    @GetMapping(value = "/")
    public String index(@AuthenticationPrincipal OAuth2User oAuth2User, HttpServletRequest request) {
        log.info("请求：{}", request);
        return "index " + oAuth2User.getName();
    }
}
