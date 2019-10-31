package com.luzhiqing.common.token;

public class MpUserInfo implements UserInfo {
    private Boolean admin;
    private String nickName;
    private String uid;

    public MpUserInfo(Boolean admin, String nickName, String uid) {
        this.admin = admin;
        this.nickName = nickName;
        this.uid = uid;
    }

    @Override
    public Boolean getAdmin() {
        return null;
    }

    @Override
    public String getNickname() {
        return null;
    }

    @Override
    public int getUid() {
        return 0;
    }
}
