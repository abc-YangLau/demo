package com.framework.demo.transLogic;


import com.framework.aop.Retry;
import com.framework.demo.base.TransContext;
import com.framework.demo.base.TransTemplate;
import com.framework.demo.theardPool.AsyncThreadPoolConfig;
import com.framework.demo.theardPool.TaskTrans;
import com.framework.tables.perAccountInfo.perAccountInfoDAO.PerAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/MyTrans")
//@EnableAsync
public class Test {

    @Autowired
    AsyncThreadPoolConfig asyncThreadPoolConfig;
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


    @RequestMapping("/AsyncTest")
    public void exAsyncTest() {
        //创建线程池
        AsyncTaskExecutor ser = asyncThreadPoolConfig.asyncTaskExecutor();
        for (int i = 0; i < 10; i++) {
            TransContext trans = new TransContext();
            trans.setAmtTr(new BigDecimal(i));
            trans.setDateReq("202220430");
            TaskTrans t1 = new TaskTrans(new CompeleteTask(), trans);
            ser.submit(t1);
        }
    }

    @Async("asyncTaskExecutor")
    @RequestMapping("/getAccount")
    public void getAll() {
//        return perAccountDao.getOne("622830127883478");
        for (int i = 0; i < 10; i++) {
            TransContext trans = new TransContext();
            trans.setAmtTr(new BigDecimal(i));
            trans.setDateReq("20210719");
            TransTemplate.getInstance().tansExecute(new CompeleteTask(), trans);
        }

    }


}
