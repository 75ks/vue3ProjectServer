package com.c4c.vue3ProjectServer.service;

import com.c4c.vue3ProjectServer.form.AuthUser;
import com.c4c.vue3ProjectServer.form.SignInReq;

public interface SessionService {
    /**
     * ログイン
     * @param SignInReq
     */
    public AuthUser signIn(SignInReq reqForm);
}
