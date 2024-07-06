package com.icezhg.sunflower.task;

import com.icezhg.sunflower.service.SessionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by zhongjibing on 2023/06/25.
 */
@Component
public class SessionCleanTask {

    private final SessionService sessionService;

    public SessionCleanTask(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Scheduled(cron = "30 0/1 * * * ?" )
    public void cleanExpiredSession() {
        sessionService.cleanExpired();
    }
}
