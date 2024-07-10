package com.icezhg.sunflower.pojo.query;

import com.icezhg.sunflower.util.SecurityUtil;
import lombok.Getter;

import java.util.Collection;
import java.util.Date;

/**
 * Created by zhongjibing on 2023/07/06.
 */
@Getter
public class DeleteQuery implements Query {

    private final Object data;
    private final Object list;

    private final Object user;

    private final Date date;

    private DeleteQuery(Object data, Object user, Date date) {
        if (data instanceof Collection) {
            this.data = null;
            this.list = data;

        } else {
            this.data = data;
            this.list = null;
        }
        this.user = user;
        this.date = date;
    }

    public static DeleteQuery of(Object key) {
        return new DeleteQuery(key, SecurityUtil.currentUserName(), new Date());
    }

}
