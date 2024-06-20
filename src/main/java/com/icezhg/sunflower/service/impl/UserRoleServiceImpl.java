package com.icezhg.sunflower.service.impl;

import com.icezhg.commons.exception.InvalidParameterException;
import com.icezhg.sunflower.dao.UserRoleDao;
import com.icezhg.sunflower.domain.Role;
import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.RoleAuth;
import com.icezhg.sunflower.pojo.UserAuth;
import com.icezhg.sunflower.pojo.UserInfo;
import com.icezhg.sunflower.pojo.query.NameQuery;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.pojo.query.UserQuery;
import com.icezhg.sunflower.service.RoleService;
import com.icezhg.sunflower.service.UserRoleService;
import com.icezhg.sunflower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleDao userRoleDao;

    private UserService userService;

    private RoleService roleService;

    public UserRoleServiceImpl(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public UserAuth findAuth(Long userId) {
        UserAuth userAuth = new UserAuth();
        UserInfo user = userService.findById(userId);
        if (user != null) {
            userAuth.setId(user.getId());
            userAuth.setUsername(user.getUsername());
            userAuth.setNickname(user.getNickname());
        }

        Set<Integer> grantedRoles =
                roleService.findUserRoles(userId).stream().map(Role::getId).collect(Collectors.toSet());
        List<RoleAuth> roleAuths = roleService.listAll().stream().map(role -> {
            RoleAuth roleAuth = new RoleAuth();
            roleAuth.setId(role.getId());
            roleAuth.setName(role.getName());
            roleAuth.setRoleKey(role.getRoleKey());
            roleAuth.setRoleSort(role.getRoleSort());
            roleAuth.setCreateTime(role.getCreateTime());
            roleAuth.setRemark(role.getRemark());
            roleAuth.setGranted(grantedRoles.contains(role.getId()));
            return roleAuth;
        }).toList();
        userAuth.setRoleAuths(roleAuths);
        return userAuth;
    }

    @Override
    @Transactional
    public UserAuth updateUserAuth(Long userId, List<Integer> roleIds) {
        Set<Integer> delRoles = roleService.findUserRoles(userId).stream().map(Role::getId).collect(Collectors.toSet());
        Set<Integer> addRoles = new HashSet<>(roleIds);
        Iterator<Integer> iterator = addRoles.iterator();
        while (iterator.hasNext()) {
            Integer roleId = iterator.next();
            if (delRoles.contains(roleId)) {
                delRoles.remove(roleId);
                iterator.remove();
            }
        }

        if (!addRoles.isEmpty()) {
            userRoleDao.addUserRoles(userId, addRoles);
        }
        if (!delRoles.isEmpty()) {
            userRoleDao.deleteUserRoles(userId, delRoles);
        }

        return findAuth(userId);
    }

    @Override
    public PageResult listAllocatedUsers(Integer roleId, NameQuery query) {
        UserQuery userQuery = new UserQuery();
        userQuery.setRoleId(roleId);
        userQuery.setName(query.getName());
        userQuery.setPageNum(query.getPageNum());
        userQuery.setPageSize(query.getPageSize());
        return getUserInfoPageResult(userQuery);
    }

    @Override
    public PageResult listUnallocatedUsers(Integer roleId, NameQuery query) {
        UserQuery userQuery = new UserQuery();
        userQuery.setNonRoleId(roleId);
        userQuery.setName(query.getName());
        userQuery.setPageNum(query.getPageNum());
        userQuery.setPageSize(query.getPageSize());
        return getUserInfoPageResult(userQuery);
    }

    private PageResult getUserInfoPageResult(Query query) {
        int total = userService.count(query);
        List<UserInfo> userInfos = userService.find(query);
        return new PageResult(total, userInfos);
    }

    @Override
    public void authUser(Integer roleId, List<Long> userIds) {
        if (roleId == null || userIds == null || userIds.isEmpty()) {
            throw new InvalidParameterException("", "parameter error");
        }

        List<Long> actual = userIds.stream().filter(item -> item != null && item > 0L).collect(Collectors.toList());
        userRoleDao.addRoleUsers(roleId, actual);
    }

    @Override
    public void cancelAuth(Integer roleId, List<Long> userIds) {
        if (roleId == null || userIds == null || userIds.isEmpty()) {
            throw new InvalidParameterException("", "parameter error");
        }

        userRoleDao.deleteRoleUsers(roleId, userIds);
    }
}
