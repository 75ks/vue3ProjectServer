package com.c4c.vue3ProjectServer.form;

import lombok.Data;

/**
 * クライアントに渡すBean
 */
@Data
public class UserRes {
    /**
     * テーブル；USER
     * カラム：USER_ID
     */
    private int userId;

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
