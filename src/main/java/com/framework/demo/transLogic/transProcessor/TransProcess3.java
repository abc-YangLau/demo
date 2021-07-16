package com.framework.demo.transLogic.transProcessor;

import com.framework.demo.base.BaseTrans;
import com.framework.demo.base.TransContext;
import org.springframework.stereotype.Component;

@Component
public class TransProcess3 extends BaseTrans {
    @Override
    public void processHandle(TransContext transContext) {
        System.out.println("第3个业务处理逻辑！");
    }
}
