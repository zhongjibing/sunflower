package com.icezhg.sunflower.pojo.query;

import lombok.Getter;

/**
 * Created by zhongjibing on 2022/09/04.
 */
@Getter
public class PageQuery extends FuzzyQuery {
    private int pageNum = 1;
    private int pageSize = 10;
    private int offset = 0;

    public void setPageNum(Integer pageNum) {
        if (pageNum != null && pageNum > 0) {
            this.pageNum = pageNum;

            resetOffset();
        }
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != null && pageSize > 0) {
            this.pageSize = pageSize;

            resetOffset();
        }
    }

    private void resetOffset() {
        this.offset = (pageNum - 1) * pageSize;
    }
}
