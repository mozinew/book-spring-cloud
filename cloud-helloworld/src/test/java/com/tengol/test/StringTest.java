package com.tengol.test;

/**
 * StringTest
 *
 * @author dongrui
 * @date 2019/12/9 11:17
 */
public class StringTest {
    public static void main(String[] args) {
//        String a1 = "ab";
//        String a2 = new String ("a" + "b");
        String a = "Hello2";
        final String b = "Hello";
        String c = "Hello";
        String d = b + 2;
        String e = c + 2;
        System.out.println(a == d);
        System.out.println(a == e);
        String f = new String("Hello"+2).intern();
        System.out.println(a == f);
    }
}
