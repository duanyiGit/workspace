package com.duanyi.Thread;

import java.util.concurrent.TimeUnit;

/**
 * wait()和notify()实现多线程轮流打印数组
 */
public class SynWaitNotify {

    static char[]  number = {'1','2','3','4','5','6'};
    static char[]  letter = {'A','B','C','D','E','F'};
    final  static Object o  = new Object();
    static volatile boolean t2State = false;
    static  Thread t1 = null ;
    static  Thread t2 = null ;

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer();
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    t2.start();
                   /* while (!t2State){   //t2未运行时，t1不打印
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }*/

                    for (int i = 0; i < number.length; i++) {
                        System.out.print(number[i] +" ");
                        try {
                            o.notify();  //唤醒另一个线程
                            o.wait();    //该线程等待
                          // TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        o.notifyAll();  //必须要加，避免有线程一直在等待，程序不能结束
                    }
                }
            }
        },"t1");

        t2 = new Thread(()->{
            synchronized (o){
                for (int i = 0; i < letter.length; i++) {
                    t2State = true;
                    System.out.print(letter[i] +" ");
                    try {
                        o.notify();
                        o.wait();
                      //  TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    o.notifyAll();
                }
            }
        },"t2");
      /*
       t1.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        t1.start();
    }
}
