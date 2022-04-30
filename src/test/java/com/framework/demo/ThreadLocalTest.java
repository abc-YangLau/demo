package com.framework.demo;

public class ThreadLocalTest {
    //(1)创建ThreadLocal变量,子线程不继承主线程的内容，但是InheritableThreadLocal的子线程可以继续
    public static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    /**
     * 同一个ThreadLocal变量在父线程中被设置值后，在子线程中是获取不到的。
     * （threadLocals中为当前调用线程对应的本地变量，所以二者自然是不能共享的）
     *
     * @param args
     */
    public static void main(String[] args) {
        //在main线程中添加main线程的本地变量
        threadLocal.set("mainVal");
        //新创建一个子线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程中的本地变量值:" + threadLocal.get());
            }
        });
        thread.start();
        //输出main线程中的本地变量值
        System.out.println("mainx线程中的本地变量值:" + threadLocal.get());
    }
}
