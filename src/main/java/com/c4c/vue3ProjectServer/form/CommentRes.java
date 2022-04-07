package com.c4c.vue3ProjectServer.form;

import lombok.Data;

/**
 * クライアントに渡すBean
 */
@Data
public class CommentRes {
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
     * テーブル：USER
     * カラム：USER_NAME
     */
    private String userName;
}
