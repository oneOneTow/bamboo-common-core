package com.luzhiqing.common.web;

public class PageResult<T> {

    public static final int SUCCESS = 0;

    public static final int CHECK_FAIL = 1;

    public static final int UNKNOWN_EXCEPTION = -99;

    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    private PageInfo pageInfo;

    public PageResult() {
        super();
    }

    public PageResult(T data) {
        super();
        this.data = data;
    }

    public PageResult(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = UNKNOWN_EXCEPTION;
    }


    /**
     * 返回的信息出错的时候使用
     */
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 接口返回码, 0表示成功, 其他看对应的定义
     * 晓风轻推荐的做法是:
     * 0   : 成功
     * >0 : 表示已知的异常(例如提示错误等, 需要调用地方单独处理)
     * <0 : 表示未知的异常(不需要单独处理, 调用方统一处理)
     */
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
