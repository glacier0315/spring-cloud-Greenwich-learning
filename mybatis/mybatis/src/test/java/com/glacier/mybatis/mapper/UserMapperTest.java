package com.glacier.mybatis.mapper;


import com.glacier.mybatis.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2020-02-13 11:56
 */
@Transactional()
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void findById() {
    }

    @Test
    public void findList() {
    }

    @Test
    public void loadUserByUsername() {
        String admin = "admin";
        User user = userMapper.loadUserByUsername(admin);
        Assert.assertNotNull(user);
        Assert.assertEquals(admin, user.getUsername());
    }

    @Test
    public void insert() {
    }

    @Test
    public void update() {
    }
}