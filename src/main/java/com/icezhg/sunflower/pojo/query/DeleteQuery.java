package com.icezhg.sunflower.pojo.query;

import com.icezhg.sunflower.util.SecurityUtil;
import lombok.Getter;

import java.util.Date;

/**
 * Created by zhongjibing on 2023/07/06.
 */
@Getter
public class DeleteQuery implements Query {

    private final Object key;

    private final Object user;

    private final Date date;

    private DeleteQuery(Object key, Object user, Date date) {
        this.key = key;
        this.user = user;
        this.date = date;
    }

    public static DeleteQuery of(Object key) {
        return new DeleteQuery(key, SecurityUtil.currentUserName(), new Date());
    }

}
