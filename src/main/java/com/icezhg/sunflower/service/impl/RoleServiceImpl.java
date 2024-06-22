package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.dao.RoleDao;
import com.icezhg.sunflower.dao.RoleMenuDao;
import com.icezhg.sunflower.domain.Role;
import com.icezhg.sunflower.domain.RoleMenu;
import com.icezhg.sunflower.pojo.RoleInfo;
import com.icezhg.sunflower.pojo.query.RoleQuery;
import com.icezhg.sunflower.service.RoleService;
import com.icezhg.sunflower.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by zhongjibing on 2020/03/18
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    private final RoleMenuDao roleMenuDao;


    public RoleServiceImpl(RoleDao roleDao, RoleMenuDao roleMenuDao) {
        this.roleDao = roleDao;
        this.roleMenuDao = roleMenuDao;
    }

    @Override
    public int count(RoleQuery query) {
        return roleDao.count(query.toMap());
    }

    @Override
    public List<Role> find(RoleQuery query) {
        return roleDao.find(query.toMap());
    }

    @Override
    public Role findById(Integer roleId) {
        RoleQuery query = new RoleQuery();
        query.setId(roleId);
        return find(query).stream().findFirst().orElse(null);
    }

    @Override
    public RoleInfo findRoleInfo(Integer roleId) {
        return buildRoleInfo(findById(roleId));
    }

    @Override
    public List<Role> findCurrentRoles() {
        return findUserRoles(SecurityUtil.currentUserId());
    }

    @Override
    public List<Role> findUserRoles(Long userId) {
        return roleDao.findAuthRoles(userId);
    }

    @Override
    public boolean checkUnique(RoleInfo role) {
        RoleQuery query = new RoleQuery();
        query.setRoleKey(role.getRoleKey());
        List<Role> roles = find(query);
        return roles.isEmpty() || Objects.equals(role.getId(), roles.get(0).getId());
    }

    @Override
    @Transactional
    public RoleInfo save(RoleInfo role) {
        Role newRole = buildRole(role);
        newRole.setCreateTime(new Date());
        newRole.setCreateBy(SecurityUtil.currentUserName());
        newRole.setUpdateTime(new Date());
        newRole.setUpdateBy(SecurityUtil.currentUserName());
        roleDao.insert(newRole);
        if (!CollectionUtils.isEmpty(role.getMenuIds())) {
            roleMenuDao.batchInsert(buildRoleMenus(newRole.getId(), role.getMenuIds()));
        }
        role.setId(newRole.getId());
        return role;
    }

    @Override
    @Transactional
    public RoleInfo update(RoleInfo role) {
        Role existing = buildRole(role);
        existing.setUpdateTime(new Date());
        existing.setUpdateBy(SecurityUtil.currentUserName());
        roleDao.update(existing);
        roleMenuDao.deleteByRoleIds(List.of(role.getId()));
        if (!CollectionUtils.isEmpty(role.getMenuIds())) {
            roleMenuDao.batchInsert(buildRoleMenus(existing.getId(), role.getMenuIds()));
        }

        return role;
    }

    @Override
    public int changeStatus(RoleInfo roleInfo) {
        Role role = new Role();
        role.setId(roleInfo.getId());
        role.setStatus(roleInfo.getStatus());
        role.setUpdateTime(new Date());
        role.setUpdateBy(SecurityUtil.currentUserName());
        return roleDao.update(role);
    }

    private List<RoleMenu> buildRoleMenus(Integer roleId, List<Integer> menuIds) {
        return menuIds.stream().map(menuId -> new RoleMenu(roleId, menuId)).collect(Collectors.toList());
    }

    private Role buildRole(RoleInfo role) {
        Role newRole = new Role();
        newRole.setId(role.getId());
        newRole.setName(role.getName());
        newRole.setRoleKey(role.getRoleKey());
        newRole.setRoleSort(role.getRoleSort());
        newRole.setDataScope(role.getDataScope());
        newRole.setMenuCheckStrictly(role.isMenuCheckStrictly() ? Constant.YES : Constant.NO);
        newRole.setDeptCheckStrictly(role.isDeptCheckStrictly() ? Constant.YES : Constant.NO);
        newRole.setStatus(role.getStatus());
        newRole.setRemark(role.getRemark());
        return newRole;
    }

    private RoleInfo buildRoleInfo(Role role) {
        RoleInfo roleInfo = new RoleInfo();
        if (role != null) {
            roleInfo.setId(role.getId());
            roleInfo.setName(role.getName());
            roleInfo.setRoleKey(role.getRoleKey());
            roleInfo.setRoleSort(role.getRoleSort());
            roleInfo.setDataScope(role.getDataScope());
            roleInfo.setMenuCheckStrictly(role.getMenuCheckStrictly() == Constant.YES);
            roleInfo.setDeptCheckStrictly(role.getDeptCheckStrictly() == Constant.YES);
            roleInfo.setStatus(role.getStatus());
            roleInfo.setRemark(role.getRemark());
        }
        return roleInfo;
    }

    @Override
    @Transactional
    public int deleteRoles(List<Integer> roleIds) {
        roleMenuDao.deleteByRoleIds(roleIds);
        return roleDao.deleteByIds(roleIds);
    }

    @Override
    public List<Role> listAll() {
        return roleDao.listAll();
    }

}
