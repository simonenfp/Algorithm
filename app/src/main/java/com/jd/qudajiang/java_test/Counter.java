package com.jd.qudajiang.java_test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qudajiang
 * @date 2018/9/6
 */
public class Counter {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private  int i = 0;
    public static void main(String[] args){
        final Counter cas = new Counter();
        List<Thread> threadList = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 50; j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            threadList.add(t);
        }

        for (Thread t : threadList) {
            t.start();
        }

        //等待所有线程执行完成
        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * 使用CAS实现线程安全计数器
     */
    private void safeCount(){
        for (;;) {
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i,++i);
            if (suc){
                break;
            }
        }
    }

    /**
     * 非线程安全计数器
     */
    private void count(){
        i++;
    }
}
