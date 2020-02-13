package com.glacier.auth.service;

import com.glacier.auth.entity.User;
import com.glacier.common.page.PageRequest;
import com.glacier.common.page.PageResponse;

/**
 * 用户业务层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:50
 */
public interface UserService {

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    PageResponse<User> findPage(PageRequest<User> pageRequest);
}
