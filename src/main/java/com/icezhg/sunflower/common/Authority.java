package com.icezhg.sunflower.common;

/**
 * Created by zhongjibing on 2023/06/26.
 */
public interface Authority {

    String AUTHENTICATED = "AUTHENTICATED";

    interface System {

        interface User {
            String QUERY = "system:user:query";
            String ADD = "system:user:add";
            String EDIT = "system:user:edit";
            String DELETE = "system:user:delete";
            String STATUS = "system:user:status";
            String ASSIGN = "system:user:assign";
            String PWD = "system:user:pwd";
        }

        interface Role {
            String QUERY = "system:role:query";
            String ADD = "system:role:add";
            String EDIT = "system:role:edit";
            String DELETE = "system:role:delete";
            String STATUS = "system:role:status";
            String ASSIGN = "system:role:assign";
        }

        interface Menu {
            String QUERY = "system:menu:query";
            String ADD = "system:menu:add";
            String EDIT = "system:menu:edit";
            String DELETE = "system:menu:delete";
        }

        interface Config {
            String QUERY = "system:config:query";
            String ADD = "system:config:add";
            String EDIT = "system:config:edit";
            String DELETE = "system:config:delete";
        }

        interface Dict {
            String QUERY = "system:dict:query";
            String ADD = "system:dict:add";
            String EDIT = "system:dict:edit";
            String DELETE = "system:dict:delete";
        }

        interface Picture {
            String QUERY = "system:dict:query";
            String UPLOAD = "system:dict:query";
            String DELETE = "system:dict:query";
        }

    }

    interface Monitor {
        interface Online {
            String QUERY = "monitor:online:query";
            String LOGOUT = "monitor:online:logout";
        }

        interface Server {
            String QUERY = "monitor:server:query";
        }

        interface Cache {
            String QUERY = "monitor:cache:query";
        }

        interface OperationLog {
            String QUERY = "log:operation:query";
        }

        interface LoginRecord {
            String QUERY = "log:login:query";
        }

        interface Task {
            String QUERY = "monitor:task:query";
            String ADD = "monitor:task:add";
            String EDIT = "monitor:task:edit";
            String DELETE = "monitor:task:delete";
            String STATUS = "monitor:task:status";
            String RUN = "monitor:task:run";
            String LOGS = "monitor:task:logs";
        }
    }

    interface Log {
        interface Operation {
            String QUERY = "log:operation:query";
        }

        interface Login {
            String QUERY = "log:login:query";
        }
    }

    interface Resource {
        interface BanquetHall {
            String QUERY = "resource:banquet:query";
            String ADD = "resource:banquet:add";
            String EDIT = "resource:banquet:edit";
            String DELETE = "resource:banquet:delete";
        }

        interface ConferenceRoom {
            String QUERY = "resource:conference:query";
            String ADD = "resource:conference:add";
            String EDIT = "resource:conference:edit";
            String DELETE = "resource:conference:delete";
        }

        interface GuestRoom {
            String QUERY = "resource:room:query";
            String ADD = "resource:room:add";
            String EDIT = "resource:room:edit";
            String DELETE = "resource:room:delete";
        }
    }

    interface Price {
        interface BanquetHall {
            String QUERY = "price:banquet:query";
            String ADD = "price:banquet:add";
            String EDIT = "price:banquet:edit";
            String DELETE = "price:banquet:delete";
        }

        interface ConferenceRoom {
            String QUERY = "price:conference:query";
            String ADD = "price:conference:add";
            String EDIT = "price:conference:edit";
            String DELETE = "price:conference:delete";
        }

        interface GuestRoom {
            String QUERY = "price:room:query";
            String ADD = "price:room:add";
            String EDIT = "price:room:edit";
            String DELETE = "price:room:delete";
        }
    }
}
