package com.glacier.auth.controller;

import com.glacier.auth.entity.User;
import com.glacier.auth.service.UserService;
import com.glacier.common.core.http.HttpResult;
import com.glacier.common.core.page.PageRequest;
import com.glacier.common.core.page.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 分页查询用户
     *
     * @param pageRequest
     * @return
     */
    @GetMapping("findPage")
    public HttpResult<PageResponse<User>> findPage(PageRequest<User> pageRequest) {
        return HttpResult.ok(userService.findPage(pageRequest));
    }
}
