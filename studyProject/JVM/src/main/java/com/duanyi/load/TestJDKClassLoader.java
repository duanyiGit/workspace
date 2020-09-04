package com.duanyi.load;

import com.sun.crypto.provider.DESKeyFactory;
import sun.misc.Launcher;

import java.net.URL;

public class TestJDKClassLoader {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(DESKeyFactory.class.getClassLoader()
                +"==对应的父加载器=="+DESKeyFactory.class.getClassLoader().getParent());
        System.out.println(TestJDKClassLoader.class.getClassLoader()
                +"==对应的父加载器=="+TestJDKClassLoader.class.getClassLoader().getParent());

        System.out.println("===================================");
        System.out.println("bootstrapLoader加载以下文件");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urLs.length; i++) {
            System.out.println(urLs[i]);
        }
        System.out.println();
        System.out.println("ExtClassloader加载以下文件");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("AppClassLoader加载以下文件");
        System.out.println(System.getProperty("java.class.path"));
    }
}
