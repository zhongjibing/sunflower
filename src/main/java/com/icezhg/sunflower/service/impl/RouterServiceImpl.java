package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.pojo.MenuInfo;
import com.icezhg.sunflower.pojo.MenuTree;
import com.icezhg.sunflower.pojo.Router;
import com.icezhg.sunflower.service.MenuService;
import com.icezhg.sunflower.service.RouterService;
import com.icezhg.sunflower.util.SecurityUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhongjibing on 2022/09/20.
 */
@Service
public class RouterServiceImpl implements RouterService {

    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public List<Router> listRouters() {
        List<MenuTree> menuTrees = menuService.buildMenuTreeSelect();
        if (!SecurityUtil.isRootUser()) {
            menuTrees = filterNormalMenuTrees(menuTrees);
        }

        Map<Integer, MenuTree> menuTreeMap = new HashMap<>();
        menuTrees.stream().map(this::getMenuTreeMap).forEach(menuTreeMap::putAll);
        return buildRouters(menuTrees, menuTreeMap);
    }

    private List<MenuTree> filterNormalMenuTrees(List<MenuTree> menuTrees) {
        List<MenuTree> result = new ArrayList<>(menuTrees.size());
        menuTrees.forEach(tree-> {
            if (Constant.NORMAL.equals(tree.getMenu().getStatus())) {
                tree.setChildren(filterNormalMenuTrees(tree.getChildren()));
                if (!Constant.TYPE_DIR.equals(tree.getMenu().getType()) || !tree.getChildren().isEmpty()) {
                    result.add(tree);
                }
            }
        });
        return result;
    }

    private List<Router> buildRouters(List<MenuTree> menuTrees, Map<Integer, MenuTree> treeMap) {
        return menuTrees.stream().map(tree -> buildRouters(tree, treeMap)).collect(Collectors.toList());
    }

    private Router buildRouters(MenuTree tree, Map<Integer, MenuTree> treeMap) {
        MenuInfo menu = tree.getMenu();
        Router router = new Router();
        router.setHidden(isHidden(menu));
        router.setName(getRouteName(menu, treeMap));
        router.setPath(getRouterPath(menu));
        router.setComponent(getComponent(menu));
        router.setQuery(menu.getQuery());
        router.setMeta(new Router.Meta(menu.getName(), menu.getIcon(), nonCache(menu), filterLink(menu.getPath())));
        List<MenuTree> cMenus = tree.getChildren();
        if (!CollectionUtils.isEmpty(cMenus) && Constant.TYPE_DIR.equals(menu.getType())) {
            router.setAlwaysShow(true);
            router.setRedirect("noRedirect");
            router.setChildren(buildRouters(cMenus, treeMap));
        } else if (isMenuNonFrame(menu)) {
            router.setMeta(null);
            List<Router> childrenList = new ArrayList<>();
            Router children = new Router();
            children.setPath(menu.getPath());
            children.setComponent(menu.getComponent());
            children.setName(StringUtils.capitalize(menu.getPath()));
            children.setMeta(new Router.Meta(menu.getName(), menu.getIcon(), nonCache(menu),
                    filterLink(menu.getPath())));
            children.setQuery(menu.getQuery());
            childrenList.add(children);
            router.setChildren(childrenList);
        } else if (isTopMenu(menu) && isInnerLink(menu)) {
            router.setMeta(new Router.Meta(menu.getName(), menu.getIcon()));
            router.setPath("/");
            List<Router> childrenList = new ArrayList<>();
            Router children = new Router();
            String routerPath = innerLinkReplaceEach(menu.getPath());
            children.setPath(routerPath);
            children.setComponent(Constant.INNER_LINK);
            children.setName(StringUtils.capitalize(routerPath));
            children.setMeta(new Router.Meta(menu.getName(), menu.getIcon(), filterLink(menu.getPath())));
            childrenList.add(children);
            router.setChildren(childrenList);
        }
        return router;
    }

    private Map<Integer, MenuTree> getMenuTreeMap(MenuTree tree) {
        Map<Integer, MenuTree> menuTreeMap = new HashMap<>();
        menuTreeMap.put(tree.getId(), tree);
        if (CollectionUtils.isNotEmpty(tree.getChildren())) {
            tree.getChildren().forEach(child -> menuTreeMap.putAll(getMenuTreeMap(child)));
        }
        return menuTreeMap;
    }

    /**
     * 获取路由名称
     */
    private String getRouteName(MenuInfo menu, Map<Integer, MenuTree> treeMap) {
        if (isMenuNonFrame(menu)) { // 非外链并且是一级目录（类型为目录）
            return StringUtils.EMPTY;
        }

        List<String> pathList = new LinkedList<>();
        pathList.add(StringUtils.capitalize(menu.getPath()));

        MenuTree parent;
        Integer parentId = menu.getParentId();
        while ((parent = treeMap.get(parentId)) != null) {
            pathList.add(0, StringUtils.capitalize(parent.getMenu().getPath()));
            parentId = parent.getMenu().getParentId();
        }

        return StringUtils.join(pathList, '-');
    }

    /**
     * 获取路由地址
     */
    private String getRouterPath(MenuInfo menu) {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (!isTopMenu(menu) && isInnerLink(menu)) {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (isTopMenu(menu) && nonFrame(menu) && Constant.TYPE_DIR.equals(menu.getType())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuNonFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     */
    private String getComponent(MenuInfo menu) {
        String component = Constant.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuNonFrame(menu)) {
            component = menu.getComponent();
        } else if (StringUtils.isEmpty(menu.getComponent()) && !isTopMenu(menu) && isInnerLink(menu)) {
            component = Constant.INNER_LINK;
        } else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = Constant.PARENT_VIEW;
        }
        return component;
    }

    private String filterLink(String path) {
        return isHttpLink(path) ? path : null;
    }

    /**
     * 是否为菜单内部跳转
     */
    private boolean isMenuNonFrame(MenuInfo menu) {
        return isTopMenu(menu)
                && menu.getIsFrame() != null && menu.getIsFrame() == Constant.NO_FRAME
                && Constant.TYPE_MENU.equals(menu.getType());
    }

    /**
     * 是否为内链组件
     */
    private boolean isInnerLink(MenuInfo menu) {
        return nonFrame(menu) && isHttpLink(menu.getPath());
    }

    /**
     * 是否为http(s)://开头
     */
    private boolean isHttpLink(String link) {
        return StringUtils.startsWithAny(link, Constant.HTTP_SCHEMA, Constant.HTTPS_SCHEMA);
    }

    /**
     * 是否为parent_view组件
     */
    private boolean isParentView(MenuInfo menu) {
        return !isTopMenu(menu) && Constant.TYPE_DIR.equals(menu.getType());
    }

    private boolean nonFrame(MenuInfo menu) {
        return menu.getIsFrame() != null && menu.getIsFrame() == Constant.NO_FRAME;
    }

    private boolean nonCache(MenuInfo menu) {
        return menu.getIsCache() != null && menu.getIsCache() == Constant.NO_CACHE;
    }

    private boolean isTopMenu(MenuInfo menu) {
        return menu.getParentId() != null && menu.getParentId() == Constant.TOP_MENU_PARENT;
    }

    private boolean isHidden(MenuInfo menu) {
        return "1".equals(menu.getVisible());
    }

    /**
     * 内链域名特殊字符替换
     */
    private String innerLinkReplaceEach(String path) {
        return StringUtils.replaceEach(path, new String[]{Constant.HTTP_SCHEMA, Constant.HTTPS_SCHEMA}, new String[]{
                "", ""});
    }
}
