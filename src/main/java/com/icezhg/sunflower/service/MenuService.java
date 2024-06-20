package com.icezhg.sunflower.service;


import com.icezhg.sunflower.pojo.MenuInfo;
import com.icezhg.sunflower.pojo.MenuTree;
import com.icezhg.sunflower.pojo.RoleMenuTree;
import com.icezhg.sunflower.pojo.query.MenuQuery;

import java.util.List;

/**
 * Created by zhongjibing on 2022/09/07.
 */
public interface MenuService {

    MenuInfo save(MenuInfo menuInfo);

    MenuInfo update(MenuInfo menuInfo);

    List<MenuInfo> listRoleMenus();

    List<MenuTree> buildMenuTreeSelect();

    List<MenuTree> buildMenuTree(List<MenuInfo> menus);

    RoleMenuTree buildRoleMenuTreeSelect(Integer roleId);

    List<MenuInfo> list(MenuQuery query);

    MenuInfo findMenu(Integer menuId);

    int delete(Integer menuId);
}
