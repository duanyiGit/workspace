package com.duanyi.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 静态内部类
 *
 * 1).本质上是利用类的加载机制来保证线程安全
 * 2).只有在实际使用的时候，才会触发类的初始化，所以也是懒加载的一种形式。
 */
public class TestInnerClassSingleton {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        InnerClassSingleton instance = InnerClassSingleton.getInstance();
        InnerClassSingleton instance2 = InnerClassSingleton.getInstance();
        System.out.println(instance == instance2);
        System.out.println(instance );
        System.out.println(instance2 );

        //不可防止反射攻击
        Constructor<InnerClassSingleton> declaredConstructor=InnerClassSingleton.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        InnerClassSingleton innerClassSingleton=declaredConstructor.newInstance();
        System.out.println(innerClassSingleton==instance);
    }
}

class InnerClassSingleton{
    private static class InnerClassHolder{
        private static InnerClassSingleton instance= new InnerClassSingleton();
    }

    private InnerClassSingleton(){
    }

    public static InnerClassSingleton getInstance(){
        return InnerClassHolder.instance;
    }
}