package com.esen.java_kanban_rework.utils;

import java.util.Random;

public class AppUtils {
    public static String generate6Digits(){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }
}
