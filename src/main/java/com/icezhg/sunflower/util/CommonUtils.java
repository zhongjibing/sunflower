package com.icezhg.sunflower.util;

import com.icezhg.sunflower.domain.BaseEntity;

import java.util.Date;

/**
 * Created by zhongjibing on 2023/07/06.
 */
public class CommonUtils {

    public static void completeBaseInfo(BaseEntity entity) {
        if (entity.getCreateBy() == null) {
            entity.setCreateBy(SecurityUtil.currentUserName());
        }
        if (entity.getCreateTime() == null) {
            entity.setCreateTime(new Date());
        }
        if (entity.getUpdateBy() == null) {
            entity.setUpdateBy(SecurityUtil.currentUserName());
        }
        if (entity.getUpdateTime() == null) {
            entity.setUpdateTime(new Date());
        }
    }
}
