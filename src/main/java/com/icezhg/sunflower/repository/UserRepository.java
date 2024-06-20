package com.icezhg.sunflower.repository;

import com.icezhg.sunflower.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by zhongjibing on 2022/11/12.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    User findUserByUsername(String username);

    @Modifying
    @Query(value = "update User u set u.lastLoginTime = ?2 where u.username = ?1 and u.archived = 0")
    int updateLastLoginTime(String username, Date lastLoginTime);

}
