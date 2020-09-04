
package com.duanyi.singleton;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 枚举模式，可防止反射攻击
 */

public class TestEnumSingleton {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        EnumSingleton instance = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        System.out.println(instance == instance2);
        instance.print();
        instance2.print();
        //可防止反射攻击,反射不支持创建对象
        Constructor<EnumSingleton> declaredConstructor=EnumSingleton.class.getDeclaredConstructor(String.class,int.class);
        declaredConstructor.setAccessible(true);
        EnumSingleton t1 = declaredConstructor.newInstance("INSTANCE", 0 );
        System.out.println(instance==t1);
    }
}

enum EnumSingleton{

    INSTANCE;

    public  void print(){
        System.out.println(this.hashCode());
    }
}

