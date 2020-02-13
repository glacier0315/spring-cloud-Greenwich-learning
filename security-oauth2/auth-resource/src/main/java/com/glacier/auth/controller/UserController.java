package com.glacier.auth.controller;

import com.glacier.auth.entity.User;
import com.glacier.common.http.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-02-09 08:28
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "/me")
    public Object me(OAuth2Authentication oAuth2Authentication) {
        Object principal = oAuth2Authentication.getPrincipal();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("当前登录用户为: {}", principal);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return principal;
    }

    /**
     * 分页查询用户
     *
     * @param user
     * @return
     */
    @PostMapping("findList")
    public HttpResult<List<User>> findList(@RequestBody User user) {
        List<User> users = new ArrayList<>(5);
        users.add(user);
        users.add(User.builder().username(UUID.randomUUID().toString()).build());
        users.add(User.builder().username(UUID.randomUUID().toString()).build());
        users.add(User.builder().username(UUID.randomUUID().toString()).build());
        users.add(User.builder().username(UUID.randomUUID().toString()).build());
        return HttpResult.ok(users);
    }
}