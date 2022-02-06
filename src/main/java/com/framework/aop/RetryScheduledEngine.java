package com.framework.aop;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@Component
@Order
public class RetryScheduledEngine {

    private static final Logger logger = LoggerFactory.getLogger(RetryScheduledEngine.class);

    //  private static final List<RetryInvorkParam> invorkParamList = new LinkedList<>();

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;


    /**
     * 提交任务
     *
     * @param param
     */
    public void submitTask(RetryInvorkParam param) {
        // 计算执行时间
        Date now = new Date();
        if (param.getDelayed() > 0) {
            param.setNextInvorkTime(
                    DateUtils.addSeconds(now, Long.valueOf(param.getDelayed()).intValue()));
        } else {
            param.setNextInvorkTime(now);
        }
        /**
         * 当前任务执行时间大于等于间隔时间，任务执行后立即执行下一次任务。相当于连续执行了。
         * 关于定时线程池（定时任务scheduleAtFixedRate和延时任务scheduleWithFixedDelay），好多人认为设置好频率（比如1Min），
         * 它会按照这个间隔按部就班的工作。但是，如果其中一次调度任务卡住的话，不仅这次调度失败，而且整个线程池也会停在这次调度上。
         * 因为这个是一个线程在执行
         */
        ScheduledFuture scheduledFuture =
                threadPoolTaskScheduler.scheduleWithFixedDelay(
                        new RetryRunning(param), param.getNextInvorkTime(), param.getInterval() * 1000);
        param.setFuture(scheduledFuture);
    }


}
