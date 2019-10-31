package com.luzhiqing.common.web;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/29 21:43
 */
public class Opertor {
    private final static String SYS_UID = "14101310";
    private final static String SYS_NAME = "14101310";

    private String uid;
    private String name;

    public static Opertor build() {
        Opertor opertor = new Opertor();
        opertor.setUid(SYS_UID);
        opertor.setName(SYS_NAME);
        return opertor;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
