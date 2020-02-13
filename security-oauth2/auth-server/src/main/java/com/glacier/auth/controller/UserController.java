package com.glacier.auth.controller;

import com.glacier.auth.entity.User;
import com.glacier.auth.entity.dto.UserDetailsDto;
import com.glacier.auth.service.UserService;
import com.glacier.common.http.HttpResult;
import com.glacier.common.page.PageRequest;
import com.glacier.common.page.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 22:13
 */
@Slf4j
@RestController
@RequestMapping(value = "user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    /**
     * 查看当前用户信息
     *
     * @param authentication
     * @return
     */
    @GetMapping(value = "/me")
    public UserDetailsDto me(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("当前登录用户为: {}", principal);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        if (principal instanceof UserDetailsDto) {
            return (UserDetailsDto) principal;
        }
        UserDetailsDto userDetailsDto = (UserDetailsDto) userDetailsService.loadUserByUsername((String) principal);
        return userDetailsDto;
    }

    /**
     * 分页查询用户
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("findPage")
    public HttpResult<PageResponse<User>> findPage(@RequestBody PageRequest<User> pageRequest) {
        return HttpResult.ok(userService.findPage(pageRequest));
    }
}
