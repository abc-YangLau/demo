package com.framework.demo.transLogic;


import com.framework.aop.Retry;
import com.framework.demo.base.TransContext;
import com.framework.demo.base.TransTemplate;
import com.framework.demo.theardPool.AsyncThreadPoolConfig;
import com.framework.demo.theardPool.TaskTrans;
import com.framework.tables.perAccountInfo.perAccountInfoDAO.PerAccountDao;
import com.framework.tables.perAccountInfo.perAccountInfoEntity.PerAccountEntity;
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

    /**
     * 实循环提交多个线程任务，并行执行
     * 每次提交的线程任务就是丢给线程池并调用call函数执行
     * 下一次循环提交不等子线程完成
     */
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

    /**
     * 实现循环提交的多个线程任务，串行执行。
     * 因为是使用了这个@Async。表示每次提交的任务丢给线程池处理，
     * 但是下一次提交是在上一次提交的任务完成后再循环提交的。
     * 猜测这个原因：因为是主线程执行的任务提交给线程池的某个线程异步执行，一个线程在异步线程池中只能占用一个线程
     * 一个主线程值对应一个异步线程。因为即使我将注解放在TransTemplate类的tansExecute方法上也是这样的
     *
     * @return
     */
    @Async("asyncTaskExecutor")
    @RequestMapping("/getAccount")
    public PerAccountEntity getAll() {
        for (int i = 0; i < 10; i++) {
            TransContext trans = new TransContext();
            trans.setAmtTr(new BigDecimal(i));
            trans.setDateReq("20210719");
            TransTemplate.getInstance().tansExecute(new CompeleteTask(), trans);
        }
        System.out.println("ans:" + perAccountDao.getOne("622830127883478").getAccountName());
        return perAccountDao.getOne("622830127883478");
    }


}
