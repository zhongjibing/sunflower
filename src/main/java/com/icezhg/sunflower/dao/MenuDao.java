package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.Menu;
import com.icezhg.sunflower.pojo.MenuInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2022/09/07.
 */
@Repository
public interface MenuDao {

    List<MenuInfo> list(Map<String, Object> query);

    int insert(Menu menu);

    int update(Menu menu);

    MenuInfo findById(Integer id);

    int deleteById(Integer id);

    boolean hasChildren(Integer parentId);
}
