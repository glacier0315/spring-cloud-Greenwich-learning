package com.glacier.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glacier.auth.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色Mapper
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-12 10:59
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户名查找角色权限编码集合
     *
     * @param userId
     * @return
     */
    List<String> findRoleCodesByUser(@Param("userId") String userId);
}
