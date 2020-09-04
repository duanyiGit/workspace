package com.duanyi.jvm;

public class StaticTest {
      //final修饰的变量才能叫做常量
        private  final  static int a = 10;
        private static int b = 20;  //静态变量不是静态变量
        private final static  String c = "30";
        static {
            _i = 20;
        }
        public static int _i = 10;

        public static void main(String[] args) {
            System.out.println(_i);
            b = 50;
            System.out.println(b);
        }
}
