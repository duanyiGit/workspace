package com.duanyi.Thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock与Condition实现多线程轮流打印数组
 */
public class ReentrantLockDemo {
    static char[]  number = {'1','2','3','4','5','6'};
    static char[]  letter = {'A','B','C','D','E','F'};
    static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        //一个Condition
        oneCondition();
        //两个Condition
        //twoCondition();
    }
    public static void oneCondition(){
        {

            Lock lock = new ReentrantLock();
            Condition c = lock.newCondition();

            new Thread(()->{
               /* try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                lock.lock();
                try {
                    for (int i = 0; i < number.length; i++) {
                        System.out.print(number[i] +" ");
                        c.signal();
                        try {
                            c.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    c.signal();
                }finally {
                    lock.unlock();
                }
            },"t1").start();

            new Thread(()->{
                lock.lock();
                try {
                    countDownLatch.countDown();
                    for (int i = 0; i < letter.length; i++) {
                        System.out.print(letter[i] +" ");
                        c.signal();
                        try {
                            c.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    c.signal();
                } finally {
                    lock.unlock();
                }
            },"t2").start();
        }
    }

    public static void twoCondition(){
        {

            Lock lock = new ReentrantLock();
            Condition c1 = lock.newCondition();
            Condition c2 = lock.newCondition();

            new Thread(()->{
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    for (int i = 0; i < number.length; i++) {
                        System.out.print(number[i] +" ");
                        c2.signal();
                        try {
                            c1.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    c2.signal();
                }finally {
                    lock.unlock();
                }
            },"t1").start();

            new Thread(()->{
                lock.lock();
                try {
                    countDownLatch.countDown();
                    for (int i = 0; i < letter.length; i++) {
                        System.out.print(letter[i] +" ");
                        c1.signal();
                        try {
                            c2.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    c1.signal();
                } finally {
                    lock.unlock();
                }
            },"t2").start();
        }
    }
}
