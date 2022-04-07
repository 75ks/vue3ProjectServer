package com.c4c.vue3ProjectServer.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * テーブル：COMMENT
 */
@Data
public class Comment {
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
}
