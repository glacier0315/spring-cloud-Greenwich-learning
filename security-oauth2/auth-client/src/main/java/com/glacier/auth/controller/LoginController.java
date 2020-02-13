package com.glacier.auth.controller;

import com.glacier.auth.entity.dto.JwtDto;
import com.glacier.auth.entity.dto.LoginUserDto;
import com.glacier.auth.service.UserService;
import com.glacier.common.http.HttpResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-02-10 17:37
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {

    private final UserService userService;

    @PostMapping("/home")
    public HttpResult<JwtDto> login(@RequestBody LoginUserDto loginUserDto) {
        return userService.login(loginUserDto);
    }
}
