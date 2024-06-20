package com.icezhg.sunflower.service;


import com.icezhg.sunflower.domain.Role;
import com.icezhg.sunflower.pojo.RoleInfo;
import com.icezhg.sunflower.pojo.query.RoleQuery;

import java.util.List;

/**
 * Created by zhongjibing on 2020/03/18
 */
public interface RoleService {

    int count(RoleQuery query);

    List<Role> find(RoleQuery query);

    Role findById(Integer roleId);

    RoleInfo findRoleInfo(Integer roleId);

    List<Role> findCurrentRoles();

    List<Role> findUserRoles(Long userId);

    boolean checkUnique(RoleInfo role);

    RoleInfo save(RoleInfo role);

    RoleInfo update(RoleInfo role);

    int changeStatus(RoleInfo roleInfo);

    int deleteRoles(List<Integer> roleIds);

    List<Role> listAll();

}
