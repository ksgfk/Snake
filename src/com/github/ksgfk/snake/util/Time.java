package com.github.ksgfk.snake.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by KSGFK on 2018/10/27.
 */
public class Time {
    private static SimpleDateFormat df = new SimpleDateFormat("[HH:mm:ss]");

    public static String get() {
        return df.format(new Date());
    }
}
