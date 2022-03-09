package com.framework.demo.transLogic;


import com.framework.aop.Retry;
import com.framework.demo.base.TransContext;
import com.framework.demo.theardPool.TaskTrans;
import com.framework.demo.theardPool.ThreadPools;
import com.framework.tables.perAccountInfo.perAccountInfoDAO.PerAccountDao;
import com.framework.tables.perAccountInfo.perAccountInfoEntity.PerAccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.*;

@RestController
@RequestMapping(value = "/MyTrans")
@EnableAsync
public class Test {

    @Autowired
    ThreadPools.AsyncThreadPoolConfig asyncThreadPoolConfig;
    @Autowired
    PerAccountDao perAccountDao;

    @RequestMapping("/myTest")
    @Retry(delayed = 10, interval = 2, retryTimes = 10)
    public void myTest() throws ExecutionException, InterruptedException {
        TransContext trans = new TransContext();
        trans.setAmtTr(new BigDecimal(100));
        trans.setDateReq("20210719");
        TaskTrans t1 = new TaskTrans(new CompeleteTask(), trans);
        TransContext trans1 = new TransContext();
        trans1.setAmtTr(new BigDecimal(100));
        trans1.setDateReq("20210719");
        TaskTrans t2 = new TaskTrans(new CompeleteTask(), trans1);
        //创建线程池
        AsyncTaskExecutor ser = asyncThreadPoolConfig.asyncTaskExecutor();

        //提交执行
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        //获取结果
//            boolean res1 = r1.get();
//            boolean res2 = r2.get();

    }

    @Async("asyncTaskExecutor")
    @RequestMapping("/AsyncTest")
    public void exAsyncTest(){
        //创建线程池
        AsyncTaskExecutor ser = asyncThreadPoolConfig.asyncTaskExecutor();
        TransContext trans = new TransContext();
        trans.setAmtTr(new BigDecimal(100));
        trans.setDateReq("20210719");
        TaskTrans t1 = new TaskTrans(new CompeleteTask(), trans);
        ser.submit(t1);
    }

    @RequestMapping("/getAccount")
    public PerAccountEntity getAll(){
        return perAccountDao.getOne("622830127883478");
    }

}
