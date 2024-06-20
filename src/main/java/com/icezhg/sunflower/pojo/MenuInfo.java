package com.icezhg.sunflower.pojo;

import com.icezhg.sunflower.domain.Menu;
import lombok.Data;

import java.util.Date;

/**
 * Created by zhongjibing on 2022/09/07.
 */
@Data
public class MenuInfo {

    /**
     * 菜单ID
     */
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单ID
     */
    private Integer parentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 路由参数
     */
    private String query;

    /**
     * 是否为外链（0是 1否）
     */
    private Integer isFrame;

    /**
     * 是否缓存（0缓存 1不缓存）
     */
    private Integer isCache;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private String type;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    private String status;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;

    public Menu toMenu() {
        Menu menu = new Menu();
        menu.setId(id);
        menu.setName(name);
        menu.setParentId(parentId);
        menu.setOrderNum(orderNum);
        menu.setPath(path);
        menu.setComponent(component);
        menu.setQuery(query);
        menu.setIsFrame(isFrame);
        menu.setIsCache(isCache);
        menu.setType(type);
        menu.setVisible(visible);
        menu.setStatus(status);
        menu.setPerms(perms);
        menu.setIcon(icon);
        menu.setRemark(remark);
        return menu;
    }
}
