package com.framework.demo.transLogic;


import com.framework.aop.Retry;
import com.framework.demo.base.TransContext;
import com.framework.demo.base.TransTemplate;
import com.framework.demo.theardPool.AsyncThreadPoolConfig;
import com.framework.demo.theardPool.TaskTrans;
import com.framework.tables.perAccountInfo.perAccountInfoDAO.PerAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Component
public class TestThread extends Thread implements CommandLineRunner {

    AsyncThreadPoolConfig asyncThreadPoolConfig = new AsyncThreadPoolConfig();
    @Override
    public void run(){
        //创建线程池
        ThreadPoolTaskExecutor ser = asyncThreadPoolConfig.asyncTaskExecutor();
        for (int i = 0; i < 10; i++) {
            TransContext trans = new TransContext();
            trans.setAmtTr(new BigDecimal(i));
            trans.setDateReq("202220430");
            TaskTrans t1 = new TaskTrans(new CompeleteTask(), trans);
            ser.submit(t1);
        }

    }


    @Override
    public void run(String... args) throws Exception {
        this.start();
    }
}
