package com.glacier.mybatis.controller;

import com.github.pagehelper.PageInfo;
import com.glacier.common.core.http.HttpResult;
import com.glacier.common.core.page.PageRequest;
import com.glacier.mybatis.entity.User;
import com.glacier.mybatis.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2020-02-09 08:28
 */
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    /**
     * 根据用户名查找
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/find/{username}")
    public HttpResult<User> find(@PathVariable("username") String username) {
        return HttpResult.ok(userService.loadUserByUsername(username));
    }

    /**
     * 根据分页查询
     *
     * @param pageRequest
     * @return
     */
    @GetMapping(value = "/findPage")
    public HttpResult<PageInfo<User>> findPage(PageRequest<User> pageRequest) {
        return HttpResult.ok(userService.findPage(pageRequest));
    }
}