package com.icezhg.sunflower.repository;

import com.icezhg.sunflower.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhongjibing on 2022/11/12.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "select upper(r.roleKey) from Role r, UserRole ur where r.id = ur.roleId and r.status = '0' and ur.userId = ?1")
    List<String> findRoleKeysByUserId(Long userId);
}
