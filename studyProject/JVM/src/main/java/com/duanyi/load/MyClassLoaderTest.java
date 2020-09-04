package com.duanyi.load;

import java.io.FileInputStream;
import java.lang.reflect.Method;

public class MyClassLoaderTest {
    static class  MyClassLoader extends ClassLoader{
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        @Override
        public Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                return defineClass(name,data,0,data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        private byte[] loadByte(String name) throws Exception {
           String  newName = name.replaceAll("\\.","/");
            FileInputStream fis = new FileInputStream(classPath +"/"+newName+".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        //打破双亲委派机制，重写loadClass方法
       /*protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException
        {
            synchronized (MyClassLoader.class) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    long t0 = System.nanoTime();
                    //com.duanyi.load下的包用自定义类加载器的findClass去加载,其余的包由父类去加载
                    //这样即便父类加载器的classpath下有User.class也不会加载到
                    if(! name.startsWith("com.duanyi.load")){
                            c = this.getParent().loadClass(name);
                    }else{
                        c = findClass(name);
                    }

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }

                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }*/

    }

    public static void main(String[] args) throws Exception {
        //初始化自定义类加载器，会先初始化父类ClassLoader，其中会把自定
        // 义类加载器的父加载器设置为应用程序类加载器AppClassLoader
        MyClassLoader classLoader = new MyClassLoader("D:/test");
        //D盘创建 test/com/tuling/jvm 几级目录，将User类的复制类User.class丢入该目录
        Class clazz = classLoader.loadClass("com.duanyi.load.User");
        Object object = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("sout", null);
        method.invoke(object, null);
        System.out.println(clazz.getClassLoader().getClass());
        System.out.println(clazz.getClassLoader().getParent().getClass());
    }
}
