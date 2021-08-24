package com.framework.demo.transLogic.transProcessor;

import com.framework.demo.base.BaseTrans;
import com.framework.demo.base.TransContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransProcess1 extends BaseTrans {
    @Override
    public void processHandle(TransContext transContext) {
        System.out.println(Thread.currentThread().getName()+":第1个业务处理逻辑！"+transContext.getAmtTr());
        transContext.setAmtTr(transContext.getAmtTr().add(new BigDecimal(20)));
    }
}
