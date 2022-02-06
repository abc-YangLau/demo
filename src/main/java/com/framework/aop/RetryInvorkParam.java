package com.framework.aop;




import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.Future;

@Setter
@Getter
@ToString
public class RetryInvorkParam {
    /**
     * 延迟时间
     */
    private long delayed;

    /**
     * 间隔时间
     */
    private long interval;

    /**
     * 重复次数
     */
    private int retryTimes;

    /**
     * 执行的方法的对象
     */
    private Object target;

    /**
     * 执行的参数
     */
    private Object[] args;

    /**
     * 执行的方法
     */
    private Method invorkMethod;

    /**
     * 当前执行的次数
     */
    private volatile long currentTimes;

    /**
     * 下一次执行的时间
     */
    private volatile Date nextInvorkTime;

    /**
     * 是否结束这重试
     */
    private volatile boolean isEnd;
    /**
     * 调用线程的引用--用来关闭线程
     */
    private Future<Void> future;
}
