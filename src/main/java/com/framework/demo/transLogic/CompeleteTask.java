package com.framework.demo.transLogic;


import com.framework.demo.annotation.TransDescription;
import com.framework.demo.base.BaseTrans;
import com.framework.demo.base.TransContext;
import org.springframework.stereotype.Component;

@TransDescription( transProcessorList={
    "TransProcess1",
    "TransProcess2"
})
@Component
public class CompeleteTask extends BaseTrans {
    @Override
    public void processHandle(TransContext transContext) {
        System.out.println("初始化！");
    }
}
