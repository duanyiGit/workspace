package com.duanyi.jvm;

public class StringTest {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2);

        s1 = "abc";
        s2 = "abc";
        s1 = "bcd";
        System.out.println(s1 == s2);

        s1 = "abc";
        s2 = "abc";
        s1 = "bcd";
        String s3 = s1;
        String s4 = "bcd";
        System.out.println(s4 == s3);

        s1 = "ja";
        s2 = "va";
        s3 = "java";
        s4 = s1+s2;
        String s5 = "ja"+"va";
        System.out.println(s3 == s4);
        System.out.println(s4 == s5);
        System.out.println(s3 == s5);

    }
}
