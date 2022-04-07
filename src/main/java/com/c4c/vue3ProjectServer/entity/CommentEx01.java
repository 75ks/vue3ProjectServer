package com.c4c.vue3ProjectServer.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * テーブル：COMMENT、USER
 */
@Data
public class CommentEx01 {
    /**
     * テーブル；COMMENT
     * カラム：COMMENT_ID
     */
    private int commentId;

    /**
     * テーブル；COMMENT
     * カラム：USER_ID
     */
    private int userId;

    /**
     * テーブル；COMMENT
     * カラム：COMMENT_DETAIL
     */
    private String commentDetail;

    /**
     * テーブル；COMMENT
     * カラム：CREATE_DATE_TIME
     */
    private LocalDateTime createDateTime;

    /**
     * テーブル；COMMENT
     * カラム：DELETE_FLG
     */
    private LocalDateTime updateDateTime;

    /**
     * テーブル；COMMENT
     * カラム：DELETE_FLG
     */
    private int deleteFlg;

    /**
     * テーブル：USER
     * カラム：USER_NAME
     */
    private String userName;
}
