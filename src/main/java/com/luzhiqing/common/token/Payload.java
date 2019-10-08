package com.luzhiqing.common.token;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/2 10:03
 */
public class Payload implements UserInfo{
    private String id;
    private String issuer;
    private Long expiration;
    private String subject;
    private Long published;
    private int uid;
    private String nickname;
    private Boolean admin;

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getPublished() {
        return published;
    }

    public void setPublished(Long published) {
        this.published = published;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    @Override
    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
