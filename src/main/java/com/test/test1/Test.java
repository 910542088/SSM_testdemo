package com.test.test1;

import java.util.ResourceBundle;

public class Test {

    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("com/test/test1/key");
        System.out.println(bundle.getString("key1"));

    }
}
