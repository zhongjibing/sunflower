package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.RoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhongjibing on 2022/09/08.
 */
@Repository
public interface RoleMenuDao {

    int batchInsert(List<RoleMenu> records);

    int deleteByRoleIds(List<Integer> roleIds);

    List<Integer> findMenuIdsByRoleId(Integer roleId);

    boolean checkMenuExistRole(Integer menuId);
}
