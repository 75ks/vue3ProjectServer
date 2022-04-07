package com.c4c.vue3ProjectServer.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * テーブル；USER
 */
@Data
public class User {
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

    /**
     * テーブル：USER
     * カラム：CREATE_DATE_TIME
     */
    private LocalDateTime createDateTime;

    /**
     * テーブル：USER
     * カラム；UPDATE_DATE_TIME
     */
    private LocalDateTime updateDateTime;

    /**
     * テーブル；USER
     * カラム：DELETE_FLG
     */
    private int deleteFlg;
}
