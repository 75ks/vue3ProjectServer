package com.c4c.vue3ProjectServer.form;

import lombok.Data;

/**
 * クライアントから受け取るBean
 */
@Data
public class CommentUpdateReq {
    /**
     * テーブル：COMMENT
     * カラム：COMMENT_ID
     */
    private int commentId;

    /**
     * テーブル：COMMENT
     * カラム：COMMENT_DETAIL
     */
    private String commentDetail;
}
