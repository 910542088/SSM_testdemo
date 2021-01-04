package com.test.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Pwd2Md5 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String password = "power";
        byte[] pwd = MessageDigest.getInstance("MD5").digest(password.getBytes(StandardCharsets.UTF_8));
        System.out.println(pwd);

    }
}
