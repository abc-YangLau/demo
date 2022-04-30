package com.framework.demo;

import com.framework.demo.base.BaseTrans;
import com.framework.demo.base.TransContext;

public class TransProcessTest extends BaseTrans {

//    @Override
//    public void process(TransContext transContext) throws Throwable {
//        System.out.println("-------"+"process"+"--------");
//        System.out.println(Thread.currentThread());
//    }

    @Override
    public void processHandle(TransContext transContext) {
        System.out.println("-------" + "processHandle" + "--------");
        System.out.println(transContext.getAmtTr());
        System.out.println(transContext.getDateReq());
        System.out.println(Thread.currentThread());
    }

}
