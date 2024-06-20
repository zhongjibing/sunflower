package com.icezhg.sunflower.service;

import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.UserAuth;
import com.icezhg.sunflower.pojo.query.NameQuery;

import java.util.List;

public interface UserRoleService {

    UserAuth findAuth(Long userId);

    UserAuth updateUserAuth(Long userId, List<Integer> roleIds);

    PageResult listAllocatedUsers(Integer roleId, NameQuery query);

    PageResult listUnallocatedUsers(Integer roleId, NameQuery query);

    void authUser(Integer roleId, List<Long> userIds);

    void cancelAuth(Integer roleId, List<Long> userIds);
}
