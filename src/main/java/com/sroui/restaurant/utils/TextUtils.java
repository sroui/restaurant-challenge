package com.sroui.restaurant.utils;

public class TextUtils {
    private TextUtils() {
    }

    public static boolean startsWithNumber(String s) {
        char ch = s.charAt(0);
        return Character.isDigit(ch);
    }

    public static boolean isNotBlank(String s) {
        return !(s == null || s.isEmpty());
    }
}
