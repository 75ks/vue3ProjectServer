package com.c4c.vue3ProjectServer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c4c.vue3ProjectServer.form.AuthUser;
import com.c4c.vue3ProjectServer.form.SignInReq;
import com.c4c.vue3ProjectServer.form.SignUpReq;
import com.c4c.vue3ProjectServer.form.AuthRes;
import com.c4c.vue3ProjectServer.service.impl.SessionServiceImpl;
import com.c4c.vue3ProjectServer.service.impl.UserServiceImpl;
import com.c4c.vue3ProjectServer.utils.CookieUtils;
import com.c4c.vue3ProjectServer.utils.JWTUtils;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    SessionServiceImpl sessionServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    /**
     * ログイン
     * @param SignInReq
     * @return AuthRes
     */
    @PostMapping("/signIn")
    public ResponseEntity<AuthRes> signIn(HttpServletResponse response, @RequestBody SignInReq reqForm) {
        try {
            // ユーザー認証
            AuthUser authUser = sessionServiceImpl.signIn(reqForm);
            String jwt = null;
            if (authUser != null) {
                // JWTを生成&検証
                jwt = JWTUtils.createJWT(reqForm);
                // Cookieを設定
                CookieUtils.setCookie(response, "jwt", jwt);
            }
            // レスポンスを作成
            AuthRes resForm = new AuthRes();
            resForm.setJwt(jwt);
            resForm.setAuthUser(authUser);
            return ResponseEntity.ok(resForm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 新規登録 & ログイン
     * @param SignUpReq
     * @return AuthRes
     */
    @PostMapping("/signUp")
    public ResponseEntity<AuthRes> signUp(HttpServletResponse response, @RequestBody SignUpReq reqForm) throws Exception {
        try {
            // ユーザー登録
            userServiceImpl.create(reqForm);
            // ユーザー認証
            SignInReq signInReq = new SignInReq();
            BeanUtils.copyProperties(reqForm, signInReq);
            AuthUser authUser = sessionServiceImpl.signIn(signInReq);
            // JWTを生成&検証
            String jwt = JWTUtils.createJWT(signInReq);
            // Cookieを設定
            CookieUtils.setCookie(response, "jwt", jwt);
            // レスポンスを作成
            AuthRes resForm = new AuthRes();
            resForm.setJwt(jwt);
            resForm.setAuthUser(authUser);
            return ResponseEntity.ok(resForm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ログアウト
     */
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            CookieUtils.deleteCookie(request, response, "jwt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
