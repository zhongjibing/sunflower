package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.entity.IpLocation;
import com.icezhg.sunflower.entity.User;
import com.icezhg.sunflower.pojo.UserDetail;
import com.icezhg.sunflower.repository.IpLocationRepository;
import com.icezhg.sunflower.repository.RoleRepository;
import com.icezhg.sunflower.repository.UserRepository;
import com.icezhg.sunflower.service.UserService;
import com.icezhg.sunflower.util.IPAddressUtil;
import com.icezhg.sunflower.util.IpUtil;
import com.icezhg.sunflower.util.Requests;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by zhongjibing on 2023/06/20.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private IpLocationRepository ipLocationRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setIpLocationRepository(IpLocationRepository ipLocationRepository) {
        this.ipLocationRepository = ipLocationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("'" + username + "' is not exist!");
        }

        List<String> roles = roleRepository.findRoleKeysByUserId(user.getId());
        Set<GrantedAuthority> authorities = roles.stream().map(this::buildAuthority).collect(Collectors.toSet());

        return UserDetail.builder()
                .id(String.valueOf(user.getId()))
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .birthdate(user.getBirthdate())
                .email(user.getEmail())
                .mobile("**********" + StringUtils.substring(user.getMobile(), StringUtils.length(user.getMobile()) - 1))
                .avatar(user.getAvatar())
                .createTime(String.valueOf(user.getCreateTime().getTime()))
                .updateTime(String.valueOf(user.getUpdateTime().getTime()))
                .authorities(authorities)
                .accountNonExpired(user.accountNonExpired())
                .accountNonLocked(user.accountNonLocked())
                .credentialsNonExpired(user.credentialsNonExpired())
                .attributes(attributeMap())
                .build();
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
        IpLocation ipLocation = ipLocationRepository.findByIp(ip);
        if (ipLocation != null) {
            return ipLocation.getLocation();
        }

        String location = IPAddressUtil.getLocation(ip);
        ipLocationRepository.createOrUpdate(new IpLocation(ip, location));
        return location;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    @Transactional
    public void updateLastLoginTime(String username) {
        userRepository.updateLastLoginTime(username, new Date());
    }
}
