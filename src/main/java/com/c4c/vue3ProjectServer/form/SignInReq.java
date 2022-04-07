package com.c4c.vue3ProjectServer.form;

import lombok.Data;

/**
 * クライアントから受け取るBean
 * 「ログイン」で使用する
 */
@Data
public class SignInReq {
    /**
     * テーブル；USER
     * カラム：EMAIL
     */
    private String email;

    /**
     * テーブル；USER
     * カラム：LOGIN_PW
     */
    private String loginPw;
}
