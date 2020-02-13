package com.glacier.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glacier.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author glacier
 * @version 1.0
 * @description 用户Mapper
 * @date 2020-02-12 10:30
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户名查找对应用户
     *
     * @param username
     * @return
     */
    User loadUserByUsername(@Param("username") String username);
}
