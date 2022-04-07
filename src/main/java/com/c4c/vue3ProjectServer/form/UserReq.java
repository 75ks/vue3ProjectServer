package com.c4c.vue3ProjectServer.form;

import lombok.Data;

/**
 * クライアントから受け取るBean
 */
@Data
public class UserReq {
    /**
     * テーブル：USER
     * カラム：USER_NAME
     */
    private String userName;

    /**
     * テーブル：USER
     * カラム：USER_NAME_KANA
     */
    private String userNameKana;

    /**
     * テーブル：USER
     * カラム；EMAIL
     */
    private String email;

    /**
     * テーブル：USER
     * カラム；LOGIN_PW
     */
    private String loginPw;
}
