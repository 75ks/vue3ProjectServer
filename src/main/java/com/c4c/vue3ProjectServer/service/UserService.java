package com.c4c.vue3ProjectServer.service;

import java.util.List;

import com.c4c.vue3ProjectServer.form.SignUpReq;
import com.c4c.vue3ProjectServer.form.UserRes;

public interface UserService {
    /**
     * ユーザー情報検索
     * @param int
     */
    public UserRes search(int userId);

    /**
     * ユーザー情報取得
     */
    public List<UserRes> index();

    /**
     * ユーザー情報登録
     * @param SignUpReq
     */
    public void create(SignUpReq reqForm);
}
