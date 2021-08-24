package com.framework.demo.transLogic;


import com.framework.demo.base.TransContext;
import com.framework.demo.base.TransTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.*;

@RestController
@RequestMapping(value = "/MyTrans")
public class Test  {

    @RequestMapping("/myTest")
    public void myTest() throws ExecutionException, InterruptedException {
        TransContext trans= new TransContext();
        trans.setAmtTr(new BigDecimal(100));
        trans.setDateReq("20210719");
        TaskTrans t1 = new TaskTrans(new CompeleteTask(),trans);
        //创建线程池
        ExecutorService ser = Executors.newFixedThreadPool(2);
        try{
            //提交执行
            Future<Boolean> r1 = ser.submit(t1);
            Future<Boolean> r2 = ser.submit(t1);
            //获取结果
            boolean res1 = r1.get();
            boolean res2 = r2.get();
        }catch (Exception e){
           e.printStackTrace();
        }finally {
            //关闭服务
            ser.shutdown();
        }


    }


}
