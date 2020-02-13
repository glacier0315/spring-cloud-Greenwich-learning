package com.glacier.mybatis.mapper;

import com.glacier.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 用户Mapper
 * @date 2020-02-12 10:30
 */
public interface UserMapper {

    /**
     * 查询用户
     *
     * @param user
     * @return
     */
    User findById(User user);

    /**
     * 查询用户
     *
     * @param user
     * @return
     */
    List<User> findList(User user);

    /**
     * 根据用户名查找对应用户
     *
     * @param username
     * @return
     */
    User loadUserByUsername(@Param("username") String username);

    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    int update(User user);
}
