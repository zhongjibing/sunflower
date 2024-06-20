package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/06/20.
 */
@Repository
public interface UserDao {

    int insert(User user);

    int update(User user);

    User findUserById(Long id);

    User findUserByUsername(String username);

    int updateLastLoginTime(@Param("username") String username, @Param("lastLoginTime") Date lastLoginTime);

    int count(Map<String, Object> query);

    List<User> find(Map<String, Object> query);

    String findUserPasswd(Long userId);
}
