package com.glacier.auth.controller;

import com.glacier.auth.consumer.UserConsumer;
import com.glacier.auth.entity.User;
import com.glacier.common.core.http.HttpResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-02-09 08:28
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserConsumer userConsumer;

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
    @GetMapping("findList")
    public HttpResult<List<User>> findList(User user) {
        return userConsumer.findList(user);
    }
}