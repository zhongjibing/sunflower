package com.icezhg.sunflower.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by zhongjibing on 2022/12/28.
 */
@Component
public class PasswdConfig {

    private static UserPasswdConfig userPasswdConfig;

    private static Configs configs;

    public PasswdConfig(UserPasswdConfig userPasswdConfig, Configs configs) {
        PasswdConfig.userPasswdConfig = userPasswdConfig;
        PasswdConfig.configs = configs;
    }

    public static String userInitPasswd() {
        return userPasswdConfig.passwd;
    }

    public static String getSecret() {
        return configs.secret;
    }

    public static String getSalt() {
        return configs.salt;
    }

    @Component
    @ConfigurationProperties(prefix = "sys.user.init")
    public static class UserPasswdConfig {
        private String passwd;

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }
    }


    @Component
    @ConfigurationProperties(prefix = "sys.pw")
    public static class Configs {
        private String secret;

        private String salt;

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }
    }
}
