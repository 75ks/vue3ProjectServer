package com.c4c.vue3ProjectServer.utils;

import java.time.LocalDateTime;

public class Utils {
    /**
     * インスタンス生成できないようにprivateコンストラクタを定義
     */
    private Utils() {}

    /**
     * 現在日時を取得する場合に使用
     */
    public static final LocalDateTime CURRENT_DATE_TIME = LocalDateTime.now();

    /**
     * フラグをOFFに設定する場合に使用
     */
    public static final int OFF = 0;

    /**
     * フラグをONに設定する場合に使用
     */
    public static final int ON = 1;
    public static final String DOMAIN = "localhost";
}
