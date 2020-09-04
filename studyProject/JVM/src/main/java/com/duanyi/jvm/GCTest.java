package com.duanyi.jvm;

//添加运行JVM参数： -XX:+PrintGCDetails
public class GCTest {
    public static void main(String[] args) throws InterruptedException {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6;
       // allocation1 = new byte[60000*1024];
        /*-XX:PretenureSizeThreshold=1000 -XX:+PrintGCDetails -XX:+UseSerialGC
        设置大对象直接从年轻代进入老年代，只在Serial和parNew回收器下有效
        */
        allocation2 = new byte[8000*1024];
      /* allocation2 = new byte[8000*1024];

      allocation3 = new byte[1000*1024];
      allocation4 = new byte[1000*1024];
      allocation5 = new byte[1000*1024];
      allocation6 = new byte[1000*1024];*/
    }
}