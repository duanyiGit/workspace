package com.duanyi.jvm;

import java.util.ArrayList;

public class HeapTest {

    byte[] a = new byte[1024 * 100]; //100KB

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> heapTests = new ArrayList();
        while (true){
            heapTests.add(new HeapTest());
            Thread.sleep(10);
        }
    }
}
