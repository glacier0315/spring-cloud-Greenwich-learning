package com.glacier.auth.service;

import com.glacier.auth.entity.dto.JwtDto;
import com.glacier.auth.entity.dto.LoginUserDto;
import com.glacier.common.http.HttpResult;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-02-10 18:39
 */
public interface UserService {

    /**
     * 登录检验
     *
     * @param loginUserDto 登录封装类
     * @return 登录结果
     */
    HttpResult<JwtDto> login(LoginUserDto loginUserDto);
}
