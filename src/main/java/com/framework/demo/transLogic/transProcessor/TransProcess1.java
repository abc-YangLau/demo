package com.framework.demo.transLogic.transProcessor;

import com.framework.demo.base.BaseTrans;
import com.framework.demo.base.TransContext;
import org.springframework.stereotype.Component;

@Component
public class TransProcess1 extends BaseTrans {
    @Override
    public void processHandle(TransContext transContext) {
        System.out.println("第1个业务处理逻辑！");
        System.out.println(transContext.getAmtTr()+transContext.getDateReq());
        transContext.setAmtTr(transContext.getAmtTr()+"RMB");
    }
}
