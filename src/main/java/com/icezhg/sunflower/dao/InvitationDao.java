package com.icezhg.sunflower.dao;


import com.icezhg.sunflower.domain.Invitation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by zhongjibing on 2023/08/07.
 */
@Repository
public interface InvitationDao {

    Invitation findByUserIdAndOpenid(@Param("userId") Long userId, @Param("openid") String openid);

    int insert(Invitation record);


    int update(Invitation record);

}
