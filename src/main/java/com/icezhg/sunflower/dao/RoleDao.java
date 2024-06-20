package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2020/03/18
 */
@Repository
public interface RoleDao {

    int insert(Role role);

    int update(Role role);

    int count(Map<String, Object> params);

    List<Role> find(Map<String, Object> params);

    List<Role> findAuthRoles(Long userId);

    int deleteByIds(List<Integer> roleIds);

    List<Role> listAll();
}
