package com.c4c.vue3ProjectServer.form;

import lombok.Data;

/**
 * クライアントから受け取るBean
 */
@Data
public class CommentCreateReq {
    /**
     * テーブル：COMMENT
     * カラム：USER_ID
     */
    private int userId;

    /**
     * テーブル：COMMENT
     * カラム：COMMENT_DETAIL
     */
    private String commentDetail;
}
