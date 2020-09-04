package com.duanyi.Thread;

import java.util.concurrent.locks.LockSupport;

/**
 *  LockSupport方式实现多线程轮流打印数组
 */
public class LockSupportDemo {
    static char[]  number = {'1','2','3','4','5','6'};
    static char[]  letter = {'A','B','C','D','E','F'};
    static  Thread t1 = null ;
    static  Thread t2 = null ;


    public static void main(String[] args) {
        t1 = new Thread(()->{
            for (int i = 0; i < number.length; i++) {
                LockSupport.park();  //先阻塞t1，让t2先输出
                System.out.print(number[i] +" ");
                LockSupport.unpark(t2);  //释放t2
            }
        },"t1");

       t2 =  new Thread(()->{
            for (int i = 0; i < letter.length; i++) {
                System.out.print(letter[i] +" ");
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        },"t2");
       t1.start();
       t2.start();
    }
}
