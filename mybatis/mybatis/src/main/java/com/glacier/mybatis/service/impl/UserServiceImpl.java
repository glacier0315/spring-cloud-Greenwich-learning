package com.glacier.mybatis.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.page.PageRequest;
import com.glacier.mybatis.entity.User;
import com.glacier.mybatis.mapper.UserMapper;
import com.glacier.mybatis.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 用户业务类
 * @date 2019-08-04 21:50
 */
@Slf4j
@Transactional(readOnly = true)
@Service(value = "UserService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    @Override
    public User loadUserByUsername(String username) {
        return userMapper.loadUserByUsername(username);
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(User user) {
        int update = 0;
        if (user.getId() != null && !user.getId().isEmpty()) {
            update = userMapper.update(user);
        } else {

            update = userMapper.insert(user);
        }
        return update;
    }

    /**
     * 分页查找
     *
     * @param pageRequest
     * @return
     */
    @Override
    public PageInfo<User> findPage(PageRequest<User> pageRequest) {
        //将参数传给这个方法就可实现物理分页.
        PageHelper.startPage(pageRequest.getCurrent(), pageRequest.getSize());
        List<User> list = userMapper.findList(pageRequest.getParams());
        return new PageInfo<>(list);
    }
}
