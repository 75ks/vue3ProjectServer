package com.c4c.vue3ProjectServer.service;

import java.util.List;

import com.c4c.vue3ProjectServer.form.CommentCreateReq;
import com.c4c.vue3ProjectServer.form.CommentRes;
import com.c4c.vue3ProjectServer.form.CommentUpdateReq;

public interface CommentService {
    /**
     * コメント情報取得
     */
    public List<CommentRes> index();

    /**
     * コメント情報登録
     * @param CommentCreateReq
     */
    public void create(CommentCreateReq reqForm);

    /**
     * コメント情報削除
     * @param commentId
     */
    public void delete(int commentId);

    /**
     * コメント情報更新
     * @param CommentCreateReq
     */
    public void update(CommentUpdateReq reqForm);
}
