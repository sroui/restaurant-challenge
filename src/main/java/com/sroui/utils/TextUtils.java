package com.sroui.utils;

public class TextUtils {
    public static final String EMPTY_STRING = "";

    private TextUtils() {
    }

    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException | NullPointerException exp) {
            return false;
        }
    }

    public static boolean isBlank(String s) {
        return s == null || s.trim().equals(EMPTY_STRING);
    }
}
