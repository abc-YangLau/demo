package com.framework.demo.theardPool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class AsyncThreadPoolConfig {

    private static final int MAX_POOL_SIZE = 5;
    private static final int CORE_POOL_SIZE = 5;
    private static final int TASK_NUM = 20;
    private static final int ACTIVE_TIME = 6;

    /**
     * Spring 已经实现的异常线程池：
     * 1. SimpleAsyncTaskExecutor：不是真的线程池，这个类不重用线程，每次调用都会创建一个新的线程。
     * 2. SyncTaskExecutor：这个类没有实现异步调用，只是一个同步操作。只适用于不需要多线程的地方
         * 3. ConcurrentTaskExecutor：Executor的适配类，不推荐使用。如果ThreadPoolTaskExecutor不满足要求时，才用考虑使用这个类
         * 4. SimpleThreadPoolTaskExecutor：是Quartz的SimpleThreadPool的类。线程池同时被quartz和非quartz使用，才需要使用此类
         * 5. ThreadPoolTaskExecutor ：最常使用，推荐。 其实质是对java.util.concurrent.ThreadPoolExecutor的包装
         * @return
         */
        @Bean("asyncTaskExecutor")
        public ThreadPoolTaskExecutor asyncTaskExecutor() {
            ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
            asyncTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
            asyncTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
            asyncTaskExecutor.setQueueCapacity(TASK_NUM);
            asyncTaskExecutor.setKeepAliveSeconds(ACTIVE_TIME);
            asyncTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
            asyncTaskExecutor.setThreadNamePrefix("async-task-thread-pool");
            asyncTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
            asyncTaskExecutor.initialize();
            return asyncTaskExecutor;
        }


}
