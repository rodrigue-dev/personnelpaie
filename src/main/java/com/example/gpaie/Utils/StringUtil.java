package com.example.gpaie.Utils;

import java.util.Random;

public class StringUtil {
    public static String randonKey(int size) {
        int leftLimit = 48;
        int rightLimit = 122;
        Random randomGenerator = new Random();
        return randomGenerator.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(size).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }
    public static String randonIntKey(int size) {

       int leftLimit = 48;
        int rightLimit = 57;
        Random randomGenerator = new Random();
        return randomGenerator.ints(leftLimit, rightLimit + 1)
                .limit(size).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }
}
