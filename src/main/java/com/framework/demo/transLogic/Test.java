package com.framework.demo.transLogic;


import com.framework.aop.Retry;
import com.framework.demo.base.TransContext;
import com.framework.demo.theardPool.TaskTrans;
import com.framework.demo.theardPool.ThreadPools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.*;

@RestController
@RequestMapping(value = "/MyTrans")
public class Test {

    @RequestMapping("/myTest")
    @Retry(delayed = 0, interval = 2, retryTimes = 10)
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
        ExecutorService ser = ThreadPools.getExecutorService();

        //提交执行
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        //获取结果
//            boolean res1 = r1.get();
//            boolean res2 = r2.get();


    }


}
