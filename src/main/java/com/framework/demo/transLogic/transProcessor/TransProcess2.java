package com.framework.demo.transLogic.transProcessor;


import com.framework.demo.annotation.ProcessException;
import com.framework.demo.base.BaseTrans;
import com.framework.demo.base.TransContext;
import org.springframework.stereotype.Component;

@ProcessException(exceptionProcessor="TransProcess3")
@Component
public class TransProcess2 extends BaseTrans {
    @Override
    public void processHandle(TransContext transContext) {
        System.out.println("第2个业务处理逻辑！");

    }
}
