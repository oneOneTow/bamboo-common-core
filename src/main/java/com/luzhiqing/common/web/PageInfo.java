package com.luzhiqing.common.web;

import lombok.Data;

public class PageInfo {
    /**
     * 总数
     */
    private int count;
    /**
     * 每页个数
     */
    private int pageSize;
    /**
     * 当前页
     */
    private int pageIndex;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
