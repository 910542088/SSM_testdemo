package com.test.util;

import java.util.UUID;

public class MyUUID {
    public static String get(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
