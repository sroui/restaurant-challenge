package com.sroui.utils;

public class TextUtils {

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
}
