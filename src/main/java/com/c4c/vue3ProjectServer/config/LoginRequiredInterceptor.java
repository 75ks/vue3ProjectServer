package com.c4c.vue3ProjectServer.config;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.c4c.vue3ProjectServer.utils.CookieUtils;
import com.c4c.vue3ProjectServer.utils.JWTUtils;

/**
 * コントローラーの実行前後の共通処理
 */
@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {
    /**
     * コントローラーの実行前の共通処理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Method method = ((HandlerMethod) handler).getMethod();
        // Cookieに保存されているJWTを取得する
        String jwt = CookieUtils.getCookie(request, "jwt");
        // JWTを検証
        boolean authFlg = JWTUtils.verifyJWT(jwt);
        if (authFlg) {
            System.out.println(method.getName() + " を処理します");
            // true:コントローラーを実行する
            return true;
        } else {
            System.out.println("処理を終了します");
            // false:コントローラーの処理を実行せずに200のレスポンスを返す
            return false;
        }
    }
}
