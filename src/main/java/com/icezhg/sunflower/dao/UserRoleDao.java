package com.icezhg.sunflower.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by zhongjibing on 2022/09/13.
 */
@Repository
public interface UserRoleDao {

    int addUserRoles(@Param("userId") Long userId, @Param("roleIds") Set<Integer> roleIds);

    int deleteUserRoles(@Param("userId") Long userId, @Param("roleIds") Set<Integer> roleIds);

    int addRoleUsers(@Param("roleId") Integer roleId, @Param("userIds") List<Long> userIds);

    int deleteRoleUsers(@Param("roleId") Integer roleId, @Param("userIds") List<Long> userIds);
}
