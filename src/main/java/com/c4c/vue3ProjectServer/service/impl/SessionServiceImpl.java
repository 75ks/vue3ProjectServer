package com.c4c.vue3ProjectServer.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.c4c.vue3ProjectServer.entity.User;
import com.c4c.vue3ProjectServer.form.AuthUser;
import com.c4c.vue3ProjectServer.form.SignInReq;
import com.c4c.vue3ProjectServer.mapper.UserMapper;
import com.c4c.vue3ProjectServer.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    UserMapper userMapper;

    /**
     * ログイン
     * @param SignInReq
     * @return AuthUser
     */
    public AuthUser signIn(SignInReq reqForm) {
        // メールアドレスをキーにユーザーを取得
        User user = userMapper.searchUsersByEmail(reqForm.getEmail());
        // ユーザーが存在する場合
        if (user != null) {
            AuthUser authUser = new AuthUser();
            // 入力されたパスワードとDBのパスワード(ハッシュ化済み)を比較
            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
            if (bcpe.matches(reqForm.getLoginPw(), user.getLoginPw())) {
                authUser.setUserId(user.getUserId());
                authUser.setUserName(user.getUserName());
                return authUser;
            }
        }
        return null;
    }
}
