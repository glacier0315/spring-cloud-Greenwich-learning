package com.glacier.mybatis.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glacier.common.core.page.PageRequest;
import com.glacier.mybatis.entity.User;
import com.glacier.mybatis.mapper.UserMapper;
import com.glacier.mybatis.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return userMapper.selectOne(new QueryWrapper<>(User.builder().username(username).build()));
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
            update = userMapper.updateById(user);
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
    public Page<User> findPage(PageRequest<User> pageRequest) {
        return userMapper.selectPage(new Page<>(pageRequest.getCurrent(), pageRequest.getSize()),
                new QueryWrapper<>(pageRequest.getParams()));
    }
}
