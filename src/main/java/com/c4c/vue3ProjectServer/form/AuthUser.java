package com.c4c.vue3ProjectServer.form;

import lombok.Data;

/**
 * AuthResで使用する
 */
@Data
public class AuthUser {
    /**
     * テーブル；USER
     * カラム：USER_ID
     */
    private int userId;
    /**
     * テーブル；USER
     * カラム：USER_NAME
     */
    private String userName;
}
