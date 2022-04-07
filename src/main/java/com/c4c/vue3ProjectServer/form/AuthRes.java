package com.c4c.vue3ProjectServer.form;

import lombok.Data;

/**
 * クライアントに渡すBean
 * 「新規登録」と「ログイン」で使用する
 */
@Data
public class AuthRes {
    /**
     * JWT
     */
    private String jwt;

    /**
     * 認証済みユーザー情報
     */
    private AuthUser authUser;
}
