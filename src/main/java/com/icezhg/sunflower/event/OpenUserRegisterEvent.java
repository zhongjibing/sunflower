package com.icezhg.sunflower.event;

import com.icezhg.sunflower.domain.Openid;
import org.springframework.context.ApplicationEvent;

import java.io.Serial;

/**
 * Created by zhongjibing on 2023/08/07.
 */
public class OpenUserRegisterEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = -3993822567786163136L;

    public OpenUserRegisterEvent(Openid openid) {
        super(openid);
    }

    public Openid getOpenid() {
        return (Openid) getSource();
    }
}
