package com.c4c.vue3ProjectServer.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    /**
     * Cookie登録
     * @param response
     * @param key
     * @param value
     */
    public static void setCookie(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        // HttpOnlyに設定(フロントからCookieを操作不可にする為)
        cookie.setHttpOnly(true);
        // 有効時間を1時間に設定
        cookie.setMaxAge(60 * 60);
        // ドメインを設定
        cookie.setDomain(Utils.DOMAIN);
        // パスを設定
        cookie.setPath("/");
        response.addCookie(cookie);
        System.out.println("Cookieを作成しました。key: " + cookie.getName() + ", value: " + cookie.getValue());
    }

    /**
     * Cookie取得
     * @param request
     * @param key
     */
    public static String getCookie(HttpServletRequest request, String key) {
        String cookieValue = null;
        // Cookieを全取得
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // 指定したkeyが存在する場合、そのCookieのvalueを設定する
                if (cookie.getName().equals(key)) {
                    cookieValue = cookie.getValue();
                }
            }
        }
        return cookieValue;
    }

    /**
     * Cookie削除
     * @param request
     * @param key
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String key) {
        // Cookieを全取得
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // 指定したkeyが存在する場合、そのCookieを削除する
                if (cookie.getName().equals(key)) {
                    cookie.setMaxAge(0);
                    cookie.setValue("");
                    cookie.setDomain(Utils.DOMAIN);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    System.out.println("Cookieを作成しました。key: " + cookie.getName());
                }
            }
        }
    }
}
