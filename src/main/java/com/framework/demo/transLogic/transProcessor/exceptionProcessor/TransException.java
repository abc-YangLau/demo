package com.framework.demo.transLogic.transProcessor.exceptionProcessor;

import com.framework.demo.base.BaseTrans;
import com.framework.demo.base.TransContext;
import org.springframework.stereotype.Component;

@Component
public class TransException extends BaseTrans {
    @Override
    public void processHandle(TransContext transContext) {
        System.out.println("异常处理！");
    }
}
