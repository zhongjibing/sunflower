package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.common.PasswdConfig;
import com.icezhg.sunflower.common.SysConfig;
import com.icezhg.sunflower.dao.UserDao;
import com.icezhg.sunflower.domain.AvatarPicture;
import com.icezhg.sunflower.domain.IpLocation;
import com.icezhg.sunflower.domain.Role;
import com.icezhg.sunflower.domain.User;
import com.icezhg.sunflower.enums.LoginMethod;
import com.icezhg.sunflower.pojo.MenuInfo;
import com.icezhg.sunflower.pojo.UserInfo;
import com.icezhg.sunflower.pojo.UserPasswd;
import com.icezhg.sunflower.pojo.UserStatus;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.pojo.query.UserQuery;
import com.icezhg.sunflower.security.UserDetail;
import com.icezhg.sunflower.service.AvatarPictureService;
import com.icezhg.sunflower.service.ConfigService;
import com.icezhg.sunflower.service.IpLocationService;
import com.icezhg.sunflower.service.MenuService;
import com.icezhg.sunflower.service.RoleService;
import com.icezhg.sunflower.service.UserService;
import com.icezhg.sunflower.util.IPAddressUtil;
import com.icezhg.sunflower.util.IpUtil;
import com.icezhg.sunflower.util.MaskSensitiveUtil;
import com.icezhg.sunflower.util.Requests;
import com.icezhg.sunflower.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by zhongjibing on 2023/06/20.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private RoleService roleService;

    private MenuService menuService;

    private IpLocationService ipLocationService;

    private AvatarPictureService avatarPictureService;

    private PasswordEncoder passwordEncoder;

    private ConfigService configService;


    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setIpLocationService(IpLocationService ipLocationService) {
        this.ipLocationService = ipLocationService;
    }

    @Autowired
    public void setAvatarPictureService(AvatarPictureService avatarPictureService) {
        this.avatarPictureService = avatarPictureService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("'" + username + "' is not exist!");
        }

        return UserDetail.builder()
                .id(String.valueOf(user.getId()))
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .birthdate(user.getBirthdate())
                .email(user.getEmail())
                .mobile(MaskSensitiveUtil.maskMobile(user.getMobile()))
                .avatar(user.getAvatar())
                .createTime(String.valueOf(user.getCreateTime().getTime()))
                .updateTime(String.valueOf(user.getUpdateTime().getTime()))
                .authorities(buildAuthority(user.getId()))
                .accountNonExpired(user.accountNonExpired())
                .accountNonLocked(user.accountNonLocked())
                .credentialsNonExpired(user.credentialsNonExpired())
                .attributes(attributeMap())
                .loginMethod(LoginMethod.WEB.getMethod())
                .build();
    }

    private Set<GrantedAuthority> buildAuthority(Long userId) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        List<Role> roles = roleService.findUserRoles(userId);
        roles.forEach(role -> authorities.add(buildAuthority(role)));

        if (User.isRoot(userId)) {
            authorities.add(new SimpleGrantedAuthority(Constant.ALL_PRIVILEGES));
        } else {
            List<MenuInfo> userMenus = menuService.listUserRoleMenus(userId);
            for (MenuInfo menu : userMenus) {
                if (StringUtils.isNotBlank(menu.getPerms())) {
                    authorities.add(new SimpleGrantedAuthority(menu.getPerms()));
                }
            }
        }

        if (authorities.isEmpty()) {
            authorities.add(buildAuthority(Constant.DEFAULT_ROLE));
        }

        return authorities;
    }

    private GrantedAuthority buildAuthority(Role role) {
        return buildAuthority(StringUtils.upperCase(role.getRoleKey(), Locale.ROOT));
    }

    private GrantedAuthority buildAuthority(String roleKey) {
        return roleKey.startsWith(Constant.ROLE_PREFIX) ?
                new SimpleGrantedAuthority(roleKey) : new SimpleGrantedAuthority(Constant.ROLE_PREFIX + roleKey);
    }

    private Map<String, String> attributeMap() {
        String requestIp = IpUtil.getRequestIp();
        String ipLocation = findAndSaveIpLocation(requestIp);
        return Map.of(Constant.ATTRIBUTE_IP, requestIp, Constant.ATTRIBUTE_IP_LOCATION, ipLocation,
                Constant.ATTRIBUTE_AGENT, Requests.userAgent());
    }

    private String findAndSaveIpLocation(String ip) {
        IpLocation ipLocation = ipLocationService.findByIp(ip);
        if (ipLocation != null) {
            return ipLocation.getLocation();
        }

        String location = IPAddressUtil.getLocation(ip);
        ipLocationService.save(new IpLocation(ip, location));
        return location;
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    @Transactional
    public void updateLastLoginTime(String username) {
        userDao.updateLastLoginTime(username, new Date());
    }

    @Override
    public boolean checkUnique(UserInfo userInfo) {
        UserQuery query = new UserQuery();
        query.setUsername(userInfo.getUsername());
        List<UserInfo> users = find(query);
        return users.isEmpty() || (users.size() == 1 && Objects.equals(userInfo.getId(), users.get(0).getId()));
    }

    @Override
    @Transactional
    public UserInfo save(UserInfo userInfo) {
        AvatarPicture avatarPicture = defaultAvatarPicture();
        avatarPictureService.create(avatarPicture);

        User newUser = buildUser(userInfo, true);
        newUser.setAvatar(avatarPicture.avatar());
        newUser.setCreateTime(new Date());
        newUser.setCreateBy(SecurityUtil.currentUserName());
        newUser.setUpdateTime(new Date());
        newUser.setUpdateBy(SecurityUtil.currentUserName());
        userDao.insert(newUser);
        userInfo.setId(newUser.getId());
        return userInfo;
    }

    @Override
    public UserInfo update(UserInfo userInfo) {
        SecurityUtil.checkExceedAuthority(userInfo.getId());

        User existing = buildUser(userInfo, false);
        existing.setUpdateTime(new Date());
        existing.setUpdateBy(SecurityUtil.currentUserName());
        userDao.update(existing);
        return userInfo;
    }

    private AvatarPicture defaultAvatarPicture() {
        return new AvatarPicture(configService.findConfig(SysConfig.DEFAULT_AVATAR_PICTURE));
    }

    private User buildUser(UserInfo userInfo, boolean newUser) {
        User user = new User();
        user.setId(userInfo.getId());
        user.setUsername(userInfo.getUsername());
        user.setName(userInfo.getName());
        user.setNickname(userInfo.getNickname());
        user.setGender(userInfo.getGender());
        user.setBirthdate(userInfo.getBirthdate());
        user.setEmail(userInfo.getEmail());
        user.setMobile(userInfo.getMobile());
        user.setRemark(userInfo.getRemark());
        if (newUser) {
            user.setId(null);
            user.setPassword(passwordEncoder.encode(defaultPasswd()));
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 1);
            user.setDeadline(calendar.getTime());
        }
        return user;
    }

    private UserInfo buildUserInfo(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setName(user.getName());
        userInfo.setUsername(user.getUsername());
        userInfo.setNickname(user.getNickname());
        userInfo.setGender(user.getGender());
        userInfo.setBirthdate(user.getBirthdate());
        userInfo.setEmail(user.getEmail());
        userInfo.setMobile(MaskSensitiveUtil.maskMobile(user.getMobile()));
        userInfo.setAvatar(user.getAvatar());
        userInfo.setDeadline(user.getDeadline());
        userInfo.setCreateTime(user.getCreateTime());
        userInfo.setUpdateTime(user.getUpdateTime());
        userInfo.setLastLoginTime(user.getLastLoginTime());
        userInfo.setCredentialsUpdateTime(user.getCredentialsUpdateTime());
        userInfo.setAccountLocked(user.getAccountLocked());
        userInfo.setRemark(user.getRemark());
        return userInfo;
    }

    private String defaultPasswd() {
        String initUserPasswd = configService.findConfig(SysConfig.INIT_USER_PASSWD);
        return StringUtils.defaultString(initUserPasswd, PasswdConfig.userInitPasswd());
    }

    @Override
    public int count(Query query) {
        return userDao.count(query.toMap());
    }

    @Override
    public List<UserInfo> find(Query query) {
        List<User> users = userDao.find(query.toMap());
        return users.stream().map(this::buildUserInfo).toList();
    }

    @Override
    public int changeStatus(UserStatus userStatus) {
        SecurityUtil.checkExceedAuthority(userStatus.userId());

        User user = new User();
        user.setId(userStatus.userId());
        user.setAccountLocked(userStatus.status());
        user.setUpdateTime(new Date());
        user.setUpdateBy(SecurityUtil.currentUserName());
        return userDao.update(user);
    }

    @Override
    public UserInfo findById(Long userId) {
        SecurityUtil.checkExceedAuthority(userId);

        User user = userDao.findUserById(userId);
        if (user == null) {
            return null;
        }

        return buildUserInfo(user);
    }

    @Override
    public int resetPasswd(UserPasswd userPasswd) {
        SecurityUtil.checkExceedAuthority(userPasswd.userId());

        User user = new User();
        user.setId(userPasswd.userId());
        user.setPassword(passwordEncoder.encode(userPasswd.passwd()));
        user.setCredentialsUpdateTime(new Date());
        user.setUpdateTime(new Date());
        user.setUpdateBy(SecurityUtil.currentUserName());
        return userDao.update(user);
    }
}
