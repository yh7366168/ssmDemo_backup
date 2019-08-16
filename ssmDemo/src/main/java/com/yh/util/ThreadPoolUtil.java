package com.yh.util;

import java.util.concurrent.*;

/**
 * @author yh 2019-6-17 16:34:04
 * 公共线程池
 * */
public class ThreadPoolUtil {

    private final static int DEFAULT_SIZE = 20;

    /**
     * 固定大小的线程池
     * */
    public static ExecutorService newFixedThreadPool(int size) {
       return Executors.newFixedThreadPool(size);
    }

    /**
     * 固定大小的线程池，默认大小20
     * */
    public static ExecutorService newFixedThreadPool(){
        return Executors.newFixedThreadPool(DEFAULT_SIZE);
    }

    public static ExecutorService newCachedThreadPool(){
        return Executors.newCachedThreadPool();
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for(int i=0; i<12; i++){
            pool.execute(new Runnable() {
                Integer sleepTime = 0;
                @Override
                public void run() {
                    try{
                        sleepTime = (int)(Math.random()*1000);
                        Thread.sleep(sleepTime);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println( Thread.currentThread().getName() + " - " + System.currentTimeMillis() + " 执行了" + sleepTime);
                }
            });

            pool.execute(()->{

            });
        }
    }
}
