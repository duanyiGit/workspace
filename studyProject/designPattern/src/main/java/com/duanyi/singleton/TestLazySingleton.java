package com.duanyi.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 单例模式定义:保证一个类只有一个实例，并且提供一个全局访问点
 * 场景：重量级的对象，不需要多个实例，如线程池，数据库连接池。
 * 私有构造方法，提供对外实例化的公用静态方法
 *
 *
 *懒汉模式，延迟加载，双重检索
 */
public class TestLazySingleton {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        LazySingleton t1 = LazySingleton.getInstance();
        LazySingleton t2 = LazySingleton.getInstance();
        System.out.println(t1 == t2);

        //不可防止反射攻击
        Constructor<LazySingleton> declaredConstructor=LazySingleton.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        LazySingleton lazySingleton = declaredConstructor.newInstance();
        System.out.println(lazySingleton==t1);
    }
}

class LazySingleton {
    private  static volatile LazySingleton instance;


    private LazySingleton() {

    }

    public static  LazySingleton getInstance(){
        if(instance == null){
            synchronized (LazySingleton.class){
                if(instance == null){
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}