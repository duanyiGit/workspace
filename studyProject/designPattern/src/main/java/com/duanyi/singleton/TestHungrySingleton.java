package com.duanyi.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 饿汉模式
 *
 * 类加载的初始化阶段就完成了实例的初始化 。
 * 本质上就是借助于jvm类加载机制，
 * 保证实例的唯一性（初始化过程只会执行一次）
 * 及线程安全（JVM以同步的形式来完成类加载的整个过程）。
 */
public class TestHungrySingleton {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
       HungrySingleton instance = HungrySingleton.getInstance();
        HungrySingleton instance2 = HungrySingleton.getInstance();
        System.out.println(instance == instance2);

        //不可防止反射攻击
        Constructor<HungrySingleton> declaredConstructor=HungrySingleton.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        HungrySingleton hungrySingleton = declaredConstructor.newInstance();
        System.out.println(hungrySingleton==instance);

    }
}

class HungrySingleton{
    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton (){

    }

    public static HungrySingleton getInstance(){
        //防止反射攻击
        if (HungrySingleton.instance!=null){
            throw new RuntimeException( " 单例不允许多个实例 " );
        }
        return instance;
    }
}
