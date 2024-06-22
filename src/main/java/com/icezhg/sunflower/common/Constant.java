package com.icezhg.sunflower.common;

import java.util.Locale;

/**
 * Created by zhongjibing on 2020/03/21
 */
public interface Constant {

    String ROLE_PREFIX = "ROLE_";

    String ALL_PRIVILEGES = "*:*:*";

    String DEFAULT_ROLE = "default";

    long UNKNOWN_USER_ID = -1L;

    int TOKEN_EXPIRE_SECONDS = 30 * 60;

    long TOKEN_EXPIRE_MILLIS = TOKEN_EXPIRE_SECONDS * 1000L;

    String ATTRIBUTE_IP = "ip";
    String ATTRIBUTE_IP_LOCATION = "loc";

    String ATTRIBUTE_AGENT = "agent";

    String LOGIN_SUCCESS = "0";

    String LOGIN_FAILURE = "1";

    /** 平台内系统用户的唯一标志 */
    String SYS_USER = "SYS_USER";

    /** 正常状态 */
    String NORMAL = "0";

    /** 异常状态 */
    String EXCEPTION = "1";

    /** 用户封禁状态 */
    String USER_DISABLE = "1";

    /** 角色封禁状态 */
    String ROLE_DISABLE = "1";

    /** 部门正常状态 */
    String DEPT_NORMAL = "0";

    /** 部门停用状态 */
    String DEPT_DISABLE = "1";

    /** 字典正常状态 */
    String DICT_NORMAL = "0";

    /** 是否为系统默认（是） */
    String SYS_YES = "Y";

    /** 是否菜单外链（是） */
    int YES_FRAME = 1;

    /** 是否菜单外链（否） */
    int NO_FRAME = 0;

    int YES_CACHE = 1;

    /** 是否菜单外链（否） */
    int NO_CACHE = 0;

    int TOP_MENU_PARENT = 0;

    /** 菜单类型（目录） */
    String TYPE_DIR = "M";

    /** 菜单类型（菜单） */
    String TYPE_MENU = "C";

    /** 菜单类型（按钮） */
    String TYPE_BUTTON = "F";

    /** Layout组件标识 */
    String LAYOUT = "Layout";

    /** ParentView组件标识 */
    String PARENT_VIEW = "ParentView";

    /** InnerLink组件标识 */
    String INNER_LINK = "InnerLink";

    /** 校验返回结果码 */
    String UNIQUE = "0";
    String NOT_UNIQUE = "1";

    /** 用户名长度限制 */
    int USERNAME_MIN_LENGTH = 2;
    int USERNAME_MAX_LENGTH = 20;

    /** 密码长度限制 */
    int PASSWORD_MIN_LENGTH = 5;
    int PASSWORD_MAX_LENGTH = 20;

    int YES = 1;
    int NO = 0;

    int MAX_UPLOAD_FILENAME_LEN = 64;

    String HTTP_SCHEMA = "http://";
    String HTTPS_SCHEMA = "https://";

    String DEFAULT_DATETIME_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";

    Locale DEFAULT_LOCALE = Locale.CHINA;

}
