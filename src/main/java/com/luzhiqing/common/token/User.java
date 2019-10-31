package com.luzhiqing.common.token;

import lombok.Data;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/27 19:24
 */
@Data
public class User {
    private String uid;
    private String nickname;
    private String avatarUrl;
    private Boolean admin;
}
