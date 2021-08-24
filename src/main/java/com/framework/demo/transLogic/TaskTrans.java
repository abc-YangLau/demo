package com.framework.demo.transLogic;

import com.framework.demo.base.BaseTrans;
import com.framework.demo.base.TransContext;
import com.framework.demo.base.TransTemplate;

import java.util.concurrent.Callable;

public class TaskTrans implements Callable<Boolean> {
    private BaseTrans transName;
    private TransContext transContext;

    public TaskTrans(BaseTrans transName, TransContext transContext) {
        this.transName = transName;
        this.transContext = transContext;
    }

    @Override
    public Boolean call() throws Exception {
        TransTemplate.getInstance().tansExecute(transName,transContext);
        return true;
    }
}
