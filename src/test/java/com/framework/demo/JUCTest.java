package com.framework.demo;

import com.framework.demo.base.TransContext;
import com.framework.demo.base.TransTemplate;
import com.framework.demo.transLogic.CompeleteTask;
import com.framework.demo.transLogic.TaskTrans;
import javafx.concurrent.Worker;
import org.apache.tomcat.util.threads.TaskQueue;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class JUCTest {
    public static void main(String[] args) throws InterruptedException {
//        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
////        ArrayList<String> list = new ArrayList<>();
//        for (int i = 0; i < 10000; i++) {
//            new Thread(()->{
//                list.add(Thread.currentThread().getName());
//            }).start();
//        }
//        Thread.sleep(3000);
//
//        System.out.println(list.size());

//        BlockingQueue<Runnable> workQueue = new BlockingDeque<Runnable>() ;

        ExecutorService es = new ThreadPoolExecutor(10,
                10,
                60,
                TimeUnit.MILLISECONDS,
                new TaskQueue(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, java.util.concurrent.ThreadPoolExecutor executor) {
                        System.out.println("拒绝方法！");
                    }
                }
        );

        try{
            for (int i = 0; i < 10; i++) {
                es.execute(new Runnable() {
                    @Override
                    public void run() {
                        TransTemplate.getInstance().tansExecute(new CompeleteTask(),new TransContext());
                    }
                });

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            es.shutdown();
        }
    }
}
