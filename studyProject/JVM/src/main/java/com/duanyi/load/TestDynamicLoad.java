package com.duanyi.load;

public class TestDynamicLoad {

    static{
        System.out.println("加载TestDynamicLoad");
    }

    public static void main(String[] args) {
        //代码块优于初始化方法执行
        new A();
        //懒加载，使用到了才会加载,
       B.invokeB();
    }

}

class A{
    static {
        System.out.println("加载A");
    }

    public A() {
        System.out.println("初始化A");
    }
}

class B{
    static {
        System.out.println("加载B");
    }

    public static void invokeB(){
        System.out.println("调用B");
    }

    public B() {
        System.out.println("初始化B");
    }
}
