package com.glacier.auth.controller;

import com.glacier.auth.entity.dto.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-02-16 16:40
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInfoController {

    private final UserDetailsService userDetailsService;

    /**
     * 查看当前用户信息
     *
     * @return
     */
    @GetMapping(value = "/userInfo")
    public UserDetailsDto userInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("当前登录用户为: {}", principal);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        if (principal instanceof UserDetailsDto) {
            return (UserDetailsDto) principal;
        }
        UserDetailsDto userDetailsDto = (UserDetailsDto) userDetailsService.loadUserByUsername((String) principal);
        return userDetailsDto;
    }
}
