package com.icezhg.sunflower.service;


import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.query.OnlineUserQuery;

public interface OnlineUserService {

    PageResult listOnlineUsers(OnlineUserQuery query);
}
